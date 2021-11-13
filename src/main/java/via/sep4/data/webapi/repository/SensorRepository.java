package via.sep4.data.webapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import via.sep4.data.webapi.model.SensorData;

@Repository
public interface SensorRepository extends CrudRepository<SensorData, Integer> {
    SensorData findById(int id);
    SensorData addSensorData(String value);
}
