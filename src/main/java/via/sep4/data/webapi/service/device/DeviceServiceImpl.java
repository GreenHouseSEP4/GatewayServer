package via.sep4.data.webapi.service.device;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device findDeviceByEUI(String EUI) throws NotFoundException {
        try {
            return deviceRepository.findDeviceByEUI(EUI);
        } catch (Exception e) {
            throw new NotFoundException("Device NOT FOUND");
        }
    }

    @Override
    public Device deleteDeviceByEUI(String EUI) {
        try {
            Device deviceToReturn = findDeviceByEUI(EUI);
            deviceRepository.deleteDeviceByEUI(EUI);
            return deviceToReturn;

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public Device saveDeviceByEUI(Device device) {
        try {
            deviceRepository.save(device);
            return device;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public Device updateDevice(Device device) {
        try {
            deviceRepository.updateDeviceByEUI(device.getEUI(), device.getMinTemperature(), device.getMaxTemperature(), device.getMinHumidity(), device.getMaxHumidity(),
                    device.getMinCO2(), device.getMaxCO2(), device.getMinLight(), device.getMaxLight(), device.getTargetTemperature(), device.getTargetHumidity(),
                    device.getTargetCO2(), device.getTargetLight(), device.getLocation());
            return device;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Device Not Found");
        }
    }
}
