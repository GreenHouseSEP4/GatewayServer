package via.sep4.data.webapi.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.service.measurement.MeasurementService;
import via.sep4.data.webapi.util.ApiKeyUtil;
import via.sep4.data.webapi.util.Constants;

@RestController
@RequestMapping(path = "/measurements")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private ApiKeyUtil util;

    @Operation(summary = "Get the latest measured data")
    @GetMapping(path ="/{eui}/latest", produces = "application/json")
    public ResponseEntity getLatestMeasurement(@RequestHeader("api-key") String apiKey,@PathVariable String eui) {
        try {
            util.checkApi(apiKey);
            return new ResponseEntity<>(measurementService.getLatestMeasurement(eui), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Queries mearements by date and time")
    @GetMapping(path = "/{eui}/periodic", produces = "application/json")
    public ResponseEntity getPeriodicMeasurements(@RequestHeader("api-key") String apiKey, @PathVariable String eui, @RequestParam String start, @RequestParam String end) {
        Date startDate;
        Date endDate;
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            util.checkApi(apiKey);
            startDate = format.parse(start);
            endDate = format.parse(end);
            List<SensorData> sensorData = measurementService.getPeriodicMeasurements(eui, startDate, endDate);
            return new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
