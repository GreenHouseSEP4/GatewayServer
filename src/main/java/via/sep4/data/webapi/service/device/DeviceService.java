package via.sep4.data.webapi.service.device;

import via.sep4.data.webapi.model.Device;

public interface DeviceService {
    Device findDeviceByEUI(String EUI);

    Device deleteDeviceByEUI(String EUI);
    void saveDeviceByEUI(Device device);

    void updateDevice(Device device);
}
