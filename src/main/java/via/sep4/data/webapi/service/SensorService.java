package via.sep4.data.webapi.service;

import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.SensorData;

@Service
public interface SensorService {
    SensorData findById(int id) throws Exception;
    SensorData addTemperature(String value) throws Exception;
}
