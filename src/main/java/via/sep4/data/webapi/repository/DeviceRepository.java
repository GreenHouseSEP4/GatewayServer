package via.sep4.data.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.sep4.data.webapi.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findDeviceByEUI(String EUI);
    void deleteDeviceByEUI(String EUI);
}
