package via.sep4.data.webapi.service.implementation;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.repository.SensorDataRepository;
import via.sep4.data.webapi.service.SensorService;

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
    public SensorData addTemperature(String value) throws Exception {
        SensorData data = new SensorData();
        data.setValue(value);
        try {
            return sensorDataRepository.save(data);
        } catch (Exception e) {
            throw new NotFoundException("Sensor repository not available.");
        }
    }
}