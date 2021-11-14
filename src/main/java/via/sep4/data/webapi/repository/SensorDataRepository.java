package via.sep4.data.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import via.sep4.data.webapi.model.SensorData;

@Repository
@Component
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
    @Query("select s from SensorData s where s.Id = :id")
    SensorData findById(int id);
    SensorData addSensorData(String value);
}
