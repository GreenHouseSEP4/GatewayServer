package via.sep4.data.webapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import via.sep4.data.webapi.model.SensorData;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

    @Query("SELECT s FROM SensorData s where s.eui =:eui order by s.id desc")
    SensorData findLatestByEUI(String eui);

    @Query(value = "SELECT * FROM SensorData s WHERE s.date BETWEEN startDate AND endDate",
    nativeQuery = true)
    List<SensorData> findByDateAndEUI(String eui, Date startDate, Date endDate);
}
