package via.sep4.data.webapi.service.measurement;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.repository.SensorDataRepository;
import via.sep4.data.webapi.util.SortByDate;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    
    @Autowired
    private SensorDataRepository sensorDataRepository;

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
    public SensorData getLatestMeasurement(String eui) {
        try {
            return sensorDataRepository.findFirstByEuiOrderByIdDesc(eui);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Sensor repository not available.");
        }
    }

    @Override
    public List<SensorData> getPeriodicMeasurements(String eui, Date startDate, Date endDate) {
        try {
            List<SensorData> all = sensorDataRepository.findByEuiAndDateBetween(eui, startDate, endDate);
            Collections.sort(all, new SortByDate());
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("The dates are out of bounds.");
        }
    }
}