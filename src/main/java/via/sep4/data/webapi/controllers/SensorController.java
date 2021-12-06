package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.service.api.ApiService;
import via.sep4.data.webapi.service.sensor.SensorService;

// TODO change controller to be per resource, one per temp, one per humidity ..
@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @Autowired
    private ApiService apiService;

    @GetMapping("/latest")
    public ResponseEntity<SensorData> getLatestMeasurement() {
        try {
            return new ResponseEntity<>(sensorService.getLatestMeasurement(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public String getKey() {
        String key = "";
        try {
            key = apiService.findById(1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }
}
