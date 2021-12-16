package via.sep4.data.webapi.service.measurement;

import java.util.*;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.SensorData;
import via.sep4.data.webapi.repository.MeasurementRepository;
import via.sep4.data.webapi.util.SortByDate;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    
    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public SensorData addMeasurement(SensorData data) throws NotFoundException {
        try {
            return measurementRepository.save(data);
        } catch (Exception e) {
            throw new NotFoundException("Data could not be saved");
        }
    }

    @Override
    public SensorData getLatestMeasurement(String eui) throws NotFoundException {
        try {
            return measurementRepository.findFirstByEuiOrderByIdDesc(eui);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Data could not be found ");
        }
    }

    @Override
    public List<SensorData> getPeriodicMeasurements(String eui, Date startDate, Date endDate) throws NotFoundException {
        try {
            List<SensorData> all = measurementRepository.findByEuiAndDateBetween(eui, startDate, endDate);
            Collections.sort(all, new SortByDate());
            return all;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Data could not be found");
        }
    }
}