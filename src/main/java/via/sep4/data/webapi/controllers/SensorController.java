package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import via.sep4.data.webapi.service.ApiService;
import via.sep4.data.webapi.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @Autowired
    private ApiService apiService;

    @GetMapping("/{id}")
    public ResponseEntity getLatestTemperature(@PathVariable int id) {
        try {
            return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addtemperature")
    public ResponseEntity addTemperature(@RequestHeader("api-key") String apiKey, @RequestBody String value) {

        String key = getKey();
        if (key.equals(apiKey)) {
            try {
                sensorService.addTemperature(value);
                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public String getKey() {
        String key = "";
        try {
            key = apiService.findById(1);
        } catch (NotFoundException e1) {
            e1.printStackTrace();
        }
        return key;
    }
}
