package via.sep4.data.webapi.service.device;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device findDeviceByEUI(String EUI) {
        try {
            return deviceRepository.findDeviceByEUI(EUI);
        } catch (Exception exception) {
            throw new RuntimeException("Repository not available!", exception);
        }
    }

    @Override
    public void deleteDeviceByEUI(String EUI) {
        try {
            deviceRepository.deleteDeviceByEUI(EUI);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void saveDeviceByEUI(Device device) {
        try {
            deviceRepository.save(device);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
