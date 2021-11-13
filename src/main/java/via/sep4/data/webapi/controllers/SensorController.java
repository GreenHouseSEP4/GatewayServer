package via.sep4.data.webapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.service.SensorService;

@RestController
public class SensorController {

    private SensorService sensorService;

    @GetMapping("/Temperature/{id}")
    public ResponseEntity<SensorData> getLatestTemperature(int id) {
        try {
            return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/AddTemperature")
    public ResponseEntity addTemperature(@RequestBody String value) {
        try {
            sensorService.addTemperature(value);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
