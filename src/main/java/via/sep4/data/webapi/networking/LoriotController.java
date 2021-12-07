package via.sep4.data.webapi.networking;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;

import org.springframework.stereotype.Component;

import via.sep4.data.webapi.model.loriot.actions.RemoteCommand;
import via.sep4.data.webapi.model.loriot.actions.DownLink;
import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.model.loriot.actions.UpLink;
import via.sep4.data.webapi.service.sensor.SensorService;

import java.beans.PropertyChangeEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoriotController {
    private Gson gson = new Gson();
    private SensorService sensorService;
    private final WebSocketClient webSocketClient;

    public LoriotController(SensorService sensorService) {
        this.sensorService = sensorService;
        webSocketClient = new WebSocketClient();
        webSocketClient.addPropertyChangeListener("Receive data", this::receiveData);
    }

    public void receiveData(PropertyChangeEvent event) {
        String receivedString = event.getNewValue().toString();
        System.out.println("Received data " + receivedString);
        UpLink message = gson.fromJson(receivedString, UpLink.class);
        if (message.getCmd().equals("rx"))
            receiveMessage(message);
    }

    private void receiveMessage(UpLink message) {
        SensorData data = processData(message);
        System.out.println("Received message: " + data);
        try {
            sensorService.addMeasurement(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private SensorData processData(UpLink message) {
        SensorData data = new SensorData();
        String[] parts = new String[5];
        int msg = Integer.parseInt(message.getData());
        String binary = Integer.toBinaryString(msg);
        Matcher matcher = Pattern.compile("^(.{16})(.{8})(.{12})(.{16})(.{2})").matcher(binary);
        if (matcher.matches()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                parts[i] = matcher.group(i + 1);
            }
        }
        int temp = 0;
        int hum = 0;
        int co2 = 0;
        int light = 0;

        for (String part : parts) {
            String tempString = part.substring(1);
            switch (part.charAt(0)) {
                case '1':
                    temp = Integer.parseInt(tempString, 2);
                    break;
                case '2':
                    hum = Integer.parseInt(tempString, 2);
                    break;
                case '3':
                    co2 = Integer.parseInt(tempString, 2);
                    break;
                case '4':
                    light = Integer.parseInt(tempString, 2);
                    break;
            }
        }
        data.setHumidity(hum);
        data.setCo2(co2);
        data.setLight(light);
        data.setTemperature(temp);
        return data;
    }

    public void send(RemoteCommand command) {
        String string = processCommand(command);
        webSocketClient.sendDownLink(string);
    }

    private String processCommand(RemoteCommand command) {
        String data = command.getValue();
        DownLink message = new DownLink(command.getDestinationPort(), true, data);
        return gson.toJson(message);
    }
}
