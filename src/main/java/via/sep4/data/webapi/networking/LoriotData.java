package via.sep4.data.webapi.networking;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;

import org.springframework.stereotype.Component;

import via.sep4.data.webapi.model.loriot.actions.Command;
import via.sep4.data.webapi.model.loriot.actions.DownLink;
import via.sep4.data.webapi.model.loriot.actions.ReadData;
import via.sep4.data.webapi.model.loriot.actions.UpLink;
import via.sep4.data.webapi.repository.SensorDataRepository;

import java.beans.PropertyChangeEvent;
import java.time.Instant;

@Component
public class LoriotData {
    private Gson gson = new Gson();
    private SensorDataRepository sensorRepository;
    private final WebsocketClient websocketClient;

    public LoriotData(SensorDataRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
        websocketClient = new WebsocketClient();
        websocketClient.addPropertyChangeListener("Receive data", this::receiveData);
        receiveMessage(new UpLink(Instant.now().toEpochMilli(), true, 1, 1, "00 00"));
    }

    public void receiveData(PropertyChangeEvent event) {
        String receivedString = event.getNewValue().toString();
        System.out.println("Received data " + receivedString);
        UpLink message = gson.fromJson(receivedString, UpLink.class);
        if (message.getCmd().equals("rx"))
            receiveMessage(message);
    }

    private void receiveMessage(UpLink message) {
        ReadData data = processData(message);
        System.out.println("Received message: " + data);
    }

    private ReadData processData(UpLink message) {
        ReadData data = new ReadData();
        Iterable<String> result = Splitter.fixedLength(4).split(message.getData());
        String[] parts = Iterables.toArray(result, String.class);

        int temp = 0;
        int hum = 0;
        int co2 = 0;
        int light = 0;

        for (String part : parts) {
            String tempString = part.substring(1);
            switch (part.charAt(0)) {
                case '1':
                    hum = Integer.parseInt(tempString, 16);
                    break;
                case '2':
                    co2 = Integer.parseInt(tempString, 16);
                    break;
                case '3':
                    temp = Integer.parseInt(tempString, 16);
                    break;
                case '4':
                    light = Integer.parseInt(tempString, 16);
                    break;
            }
        }
        data.setHumidity(hum);
        data.setCo2(co2);
        data.setLight(light);
        data.setTemperature(temp);
        return data;
    }

    public void send(Command command) {
        String string = processCommand(command);
        websocketClient.sendDownLink(string);
    }

    private String processCommand(Command command) {
        String data = command.getValue();
        DownLink message = new DownLink(command.getDestinationPort(), true, data);
        return gson.toJson(message);
    }
}
