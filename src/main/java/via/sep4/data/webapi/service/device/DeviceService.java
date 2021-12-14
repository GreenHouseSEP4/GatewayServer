package via.sep4.data.webapi.service.device;

import javassist.NotFoundException;
import via.sep4.data.webapi.model.Device;

public interface DeviceService {
    Device findDeviceByEUI(String EUI) throws NotFoundException;
    Device deleteDeviceByEUI(String EUI);
    Device saveDeviceByEUI(Device device);
    Device updateDevice(Device device);
}
