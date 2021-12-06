package via.sep4.data.webapi.service.sensor;

import java.util.Date;
import java.util.List;

import via.sep4.data.webapi.model.loriot.actions.SensorData;

public interface SensorService {
    SensorData findById(int id) throws Exception;
    SensorData getLatestMeasurement() throws Exception;
    SensorData addMeasurement(SensorData data) throws Exception;
    List<SensorData> getPeriodicMeasurements(Date startDate, Date endDate) throws Exception;
}
