package via.sep4.data.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import via.sep4.data.webapi.model.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
    @Transactional
    SensorData findById(int id);
    SensorData addSensorData(String value);
}
