package via.sep4.data.webapi.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.service.sensor.SensorService;
import via.sep4.data.webapi.util.Constants;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @GetMapping("/latest")
    public ResponseEntity<SensorData> getLatestMeasurement() {
        try {
            return new ResponseEntity<>(sensorService.getLatestMeasurement(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/periodic/start={start}&end={end}")
    public ResponseEntity<List<SensorData>> getPeriodicMeasurements(@PathVariable String start, @PathVariable String end) {
        Date startDate = null;
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            startDate = format.parse(start);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(end);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        try {
            return new ResponseEntity<>(sensorService.getPeriodicMeasurements(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
