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
            exception.printStackTrace();
            throw new RuntimeException("EUI not found");
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
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public void updateDevice(Device device) {
        try {
            System.out.println(device.getEUI());
            deviceRepository.updateDeviceByEUI(device.getEUI(), device.getMinTemperature(), device.getMaxTemperature(), device.getMinHumidity(), device.getMaxHumidity(),
                    device.getMinCO2(), device.getMaxCO2(), device.getMinLight(), device.getMaxLight(), device.getTargetTemperature(), device.getTargetHumidity(),
                    device.getTargetCO2(), device.getTargetLight());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Device Not Found");
        }
    }
}
