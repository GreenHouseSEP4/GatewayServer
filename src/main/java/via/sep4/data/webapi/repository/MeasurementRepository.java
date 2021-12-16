package via.sep4.data.webapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import via.sep4.data.webapi.model.SensorData;

@Repository
public interface MeasurementRepository extends JpaRepository<SensorData, Integer> {

    SensorData findFirstByEuiOrderByIdDesc(String eui);
    List<SensorData> findByEuiAndDateBetween(String eui, Date startDate, Date endDate);
}
