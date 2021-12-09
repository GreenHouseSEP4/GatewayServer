package via.sep4.data.webapi.service.device;

import javassist.NotFoundException;
import via.sep4.data.webapi.model.Device;

public interface DeviceService {
    Device findDeviceByEUI(String EUI);

    void deleteDeviceByEUI(String EUI);
    void saveDeviceByEUI(Device device);

    void updateDevice(Device device);
}
