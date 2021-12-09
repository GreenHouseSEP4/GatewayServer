package via.sep4.data.webapi.service.sensor;

import java.util.Date;
import java.util.List;

import via.sep4.data.webapi.model.SensorData;

public interface SensorService {
    SensorData getLatestMeasurement(String eui) throws Exception;
    SensorData addMeasurement(SensorData data) throws Exception;
    List<SensorData> getPeriodicMeasurements(String eui, Date startDate, Date endDate) throws Exception;
}
