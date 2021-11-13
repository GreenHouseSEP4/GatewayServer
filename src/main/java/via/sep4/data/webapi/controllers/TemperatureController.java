package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import via.sep4.data.webapi.dao.TemperatureDAO;
import via.sep4.data.webapi.model.Temperature;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureDAO temperatureDAO;

    @GetMapping("/Temperature")
    public ResponseEntity getLatestTemperature() {
        try {
            Temperature latestTemperature = temperatureDAO.getTemperature();
            return new ResponseEntity<>(latestTemperature, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
