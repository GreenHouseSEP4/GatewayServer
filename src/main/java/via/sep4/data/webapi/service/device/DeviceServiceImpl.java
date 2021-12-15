package via.sep4.data.webapi.service.device;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.networking.LoriotController;
import via.sep4.data.webapi.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(LoriotController.class);

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
            logger.error("Device could not be found");
            return null;
        }
    }

    @Override
    public Device saveDeviceByEUI(Device device) {
        try {
            deviceRepository.save(device);
            return device;
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.error("Device could not be saved");
            return null;
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
            logger.error("Device could not be update");
            return null;
        }
    }
}
