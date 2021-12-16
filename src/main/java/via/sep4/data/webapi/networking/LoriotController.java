package via.sep4.data.webapi.networking;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.model.loriot.DownLink;
import via.sep4.data.webapi.model.loriot.RemoteCommand;
import via.sep4.data.webapi.model.loriot.UpLink;
import via.sep4.data.webapi.service.measurement.MeasurementService;
import via.sep4.data.webapi.util.Constants;

import java.beans.PropertyChangeEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

/**
 * Class for sending and receiving data over the websocket.
 */
@Component
public class LoriotController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoriotController.class);
    private Gson gson = new Gson();
    private MeasurementService measurementService;
    private final WebSocketClient webSocketClient;

    public LoriotController(MeasurementService sensorService) {
        this.measurementService = sensorService;
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
            measurementService.addMeasurement(data);
        } catch (Exception e) {
            logger.info("Data could not be processed");
            e.printStackTrace();
        }
    }

    /**
     * This method will process the message received from the Loriot network by
     * applying a regular expression on the message and splitting it into measurements.
     * @param message
     * @return SensorData object containing the measurements.
     */
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

    /**
     * Takes the timestamp from the message as epoch milliseconds and returns
     * a Date object. One hour added so the persistence is corrent as the db
     * running in UTC at this time but the system is using local time.
     * @param message
     * @return Date object.
     */
    private Date processTimestamp(UpLink message) {
        return new Date(message.getTs() + Constants.ONE_HOUR);
    }

    public void sendRemoteCommand(String eui, RemoteCommand command) {
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
