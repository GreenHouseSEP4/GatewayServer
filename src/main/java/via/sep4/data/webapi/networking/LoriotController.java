package via.sep4.data.webapi.networking;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import via.sep4.data.webapi.model.loriot.actions.RemoteCommand;
import via.sep4.data.webapi.model.loriot.actions.DownLink;
import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.model.loriot.actions.UpLink;
import via.sep4.data.webapi.service.sensor.MeasurementService;
import via.sep4.data.webapi.util.Constants;

import java.beans.PropertyChangeEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

@Component
public class LoriotController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoriotController.class);
    
    private Gson gson = new Gson();
    
    private MeasurementService sensorService;
    
    private final WebSocketClient webSocketClient;

    public LoriotController(MeasurementService sensorService) {
        this.sensorService = sensorService;
        webSocketClient = new WebSocketClient();
        webSocketClient.addPropertyChangeListener("Receive data", this::receiveData);
    }

    public void receiveData(PropertyChangeEvent event) {
        String receivedString = event.getNewValue().toString();
        logger.info("Received data {}", receivedString);
        UpLink message = gson.fromJson(receivedString, UpLink.class);
        if (message.getCmd().equals(Constants.RECEIVE_COMMAND))
            receiveMessage(message);
    }

    private void receiveMessage(UpLink message) {
        SensorData data = processData(message);
        logger.info("Received message: {}", data);
        try {
            sensorService.addMeasurement(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SensorData processData(UpLink message) {
        SensorData data = new SensorData();
        String[] parts = new String[5];
        Matcher matcher = Pattern.compile(Constants.MESSAGE_REGEX).matcher(message.getData());
        if (matcher.matches()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                parts[i] = matcher.group(i + 1);
            }
        }
        int temp = 0;
        int hum = 0;
        int co2 = 0;
        int light = 0;

        for (int i = 0; i < parts.length - 1; i++) {
            if (i == 0) {
                temp = Integer.parseInt(parts[i], 16);
            } else if (i == 1) {
                hum = Integer.parseInt(parts[i], 16);
            } else if (i == 2) {
                co2 = Integer.parseInt(parts[i], 16) * 2;
            } else {
                light = Integer.parseInt(parts[i], 16) * 4;
            }
        }

        data.setHumidity(hum);
        data.setCo2(co2);
        data.setLight(light);
        data.setTemperature(temp);
        data.setDate(processTimestamp(message));
        data.setEui(message.getEui());
        return data;
    }

    private Date processTimestamp(UpLink message) {
        return new Date(message.getTs() + Constants.ONE_HOUR);
    }

    public void send(String eui, RemoteCommand command) {
        String data = processCommand(eui, command);
        webSocketClient.sendDownLink(data);
        logger.info("Data: {}", data);
    }

    private String processCommand(String eui, RemoteCommand command) {
        int data = command.getValue();
        String value = String.valueOf(data);
        DownLink message = new DownLink(eui, command.getDestinationPort(), true, value);
        return gson.toJson(message);
    }
}
