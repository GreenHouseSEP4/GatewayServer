package via.sep4.data.webapi.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.service.api.ApiService;
import via.sep4.data.webapi.service.sensor.SensorService;

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

    @GetMapping("/periodic/start={startdate}&end={enddate}")
    public ResponseEntity<List<SensorData>> getPeriodicMeasurements(@PathVariable String startdate, @PathVariable String enddate) {
        Date startDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            startDate = format.parse(startdate);
            startDate.setTime(startDate.getTime() + 3600);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(enddate);
            endDate.setTime(endDate.getTime() + 3600);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            return new ResponseEntity<>(sensorService.getPeriodicMeasurements(startDate, endDate), HttpStatus.OK);
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
