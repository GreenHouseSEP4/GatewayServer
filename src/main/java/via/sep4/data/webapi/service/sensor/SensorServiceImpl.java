package via.sep4.data.webapi.service.sensor;

import javassist.NotFoundException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.loriot.actions.SensorData;
import via.sep4.data.webapi.repository.SensorDataRepository;

@Service
public class SensorServiceImpl implements SensorService{
    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    public SensorData findById(int id) throws Exception {
        try {
            return sensorDataRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("Sensor repository not available.");
        }
    }

    @Override
    public SensorData addMeasurement(SensorData data) throws Exception {
        try {
            return sensorDataRepository.save(data);
        } catch (Exception e) {
            throw new NotFoundException("Sensor repository not available.");
        }
    }

    @Override
    public SensorData getLatestMeasurement() throws Exception {
        try {
            return sensorDataRepository.findFirstByOrderByIdDesc();
        } catch (Exception e) {
            throw new NotFoundException("Sensor repository not available.");
        }
    }

    @Override
    public List<SensorData> getPeriodicMeasurements(Date startDate, Date endDate) throws Exception {
        try {
            return sensorDataRepository.findByDateBetween(startDate, endDate);
        } catch (Exception e) {
            throw new NotFoundException("Sensor repository not available.");
        }
        
    }
}