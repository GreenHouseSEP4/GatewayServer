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
import via.sep4.data.webapi.util.ApiKeyUtil;
import via.sep4.data.webapi.util.Constants;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    private SensorService sensorService;

    @Autowired
    private ApiKeyUtil util;

    @GetMapping("/eui={eui}/latest")
    public ResponseEntity getLatestMeasurement(@RequestHeader("api-key") String apiKey, @RequestParam String eui) {
        try {
            util.checkApi(apiKey);
            return new ResponseEntity<>(sensorService.getLatestMeasurement(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/eui={eui}/periodic/start={start}&end={end}")
    public ResponseEntity getPeriodicMeasurements(@RequestHeader("api-key") String apiKey, @RequestParam String eui, @PathVariable String start, @PathVariable String end) {
        Date startDate;
        Date endDate;
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            util.checkApi(apiKey);
            startDate = format.parse(start);
            endDate = format.parse(end);
            List<SensorData> sensorData = sensorService.getPeriodicMeasurements(startDate, endDate);
            return new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
