package via.sep4.data.webapi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.repository.SensorRepository;
import via.sep4.data.webapi.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    public SensorRepository sensorRepository;

    @Override
    public SensorData findById(int id) throws Exception {
        try {
            return (SensorData) sensorRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Sensor repository not available.");
        }
    }

    @Override
    public SensorData addTemperature(String value) throws Exception {
        SensorData data = new SensorData(value);
        try {
            return sensorRepository.save(data);
        } catch (Exception e) {
            throw new Exception("Sensor repository not available.");
        }
    }
}
