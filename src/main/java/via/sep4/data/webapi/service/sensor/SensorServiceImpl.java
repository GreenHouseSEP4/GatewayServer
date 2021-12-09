package via.sep4.data.webapi.service.sensor;

import javassist.NotFoundException;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.repository.SensorDataRepository;
import via.sep4.data.webapi.util.SortByDate;

@Service
public class SensorServiceImpl implements SensorService {
    
    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    public SensorData findById(int id) {
        try {
            return sensorDataRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("The item by " + id + " could not be found.");
        }
    }

    @Override
    public SensorData addMeasurement(SensorData data) {
        try {
            return sensorDataRepository.save(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Sensor repository not available.");
        }
    }

    @Override
    public SensorData getLatestMeasurement() {
        try {
            return sensorDataRepository.findFirstByOrderByIdDesc();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Sensor repository not available.");
        }
    }

    @Override
    public List<SensorData> getPeriodicMeasurements(Date startDate, Date endDate) {
        try {
            List<SensorData> all = sensorDataRepository.findAllByDateBetween(startDate, endDate);
            Collections.sort(all, new SortByDate());
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("The dates are out of bounds.");
        }
    }
}