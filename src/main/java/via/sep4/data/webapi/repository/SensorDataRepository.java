package via.sep4.data.webapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import via.sep4.data.webapi.model.loriot.actions.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
    @Query("SELECT s FROM SensorData s WHERE s.id =:id")
    SensorData findById(int id);
    SensorData findFirstByOrderByIdDesc();
    List<SensorData> findAllByDateBetween(Date startDate, Date endDate);
}
