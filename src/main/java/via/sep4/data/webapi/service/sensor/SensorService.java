package via.sep4.data.webapi.service.sensor;

import via.sep4.data.webapi.model.loriot.actions.SensorData;

public interface SensorService {
    SensorData findById(int id) throws Exception;
    SensorData getLatestMeasurement() throws Exception;
    SensorData addMeasurement(SensorData data) throws Exception;
}
