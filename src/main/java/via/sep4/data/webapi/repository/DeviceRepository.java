package via.sep4.data.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import via.sep4.data.webapi.model.Device;

import javax.transaction.Transactional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findDeviceByEUI(String EUI);

    @Transactional
    @Modifying
    void deleteDeviceByEUI(String EUI);

    @Transactional
    @Modifying
    @Query("UPDATE Device d set d.minTemperature=?2, d.maxTemperature=?3, d.minHumidity=?4, d.maxHumidity=?5, d.minCO2=?6, d.maxCO2=?7, d.minLight=?8, d.maxLight=?9," +
            "d.targetTemperature=?10, d.targetHumidity=?11, d.targetCO2=?12, d.targetLight=?13, d.location=?14 WHERE d.EUI=?1")
    void updateDeviceByEUI(String eui, int minTemp, int maxTemp, int minHum, int maxHum, int minCO2, int maxCO2, int minLight, int maxLight, int targetTemp,
                           int targetHum, int targetCO2, int targetLight, String location);
}