package via.sep4.data.webapi.service;

import via.sep4.data.webapi.model.SensorData;

public interface SensorService {
    SensorData findById(int id) throws Exception;
    SensorData addTemperature(String value) throws Exception;
}
