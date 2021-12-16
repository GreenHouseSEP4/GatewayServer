package via.sep4.data.webapi.service.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.loriot.RemoteCommand;
import via.sep4.data.webapi.networking.LoriotController;

@Service
public class RemoteServiceImpl implements RemoteService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteServiceImpl.class);

    @Autowired
    private LoriotController loriotController;

    @Override
    public void setWindow(String eui, int commandPercentage) {
        try {
            if (commandPercentage <= 100 && commandPercentage >= 0) {
                loriotController.sendRemoteCommand(eui, new RemoteCommand(commandPercentage, 8));
            }
        } catch (Exception e) {
            logger.warn("Command for window cannot {}", commandPercentage);

        }
    }

    @Override
    public void setWater(String eui, int waterValue) {
        try {
            if (waterValue == 0 || waterValue == 1) {
                loriotController.sendRemoteCommand(eui, new RemoteCommand(waterValue, 9));
            }

        } catch (Exception e) {
            logger.warn("Command for window cannot {}", waterValue);
            e.printStackTrace();
        }
    }

    @Override
    public void setLight(String eui, int lightValue) {
        try {
            if (lightValue == 0 || lightValue == 1) {
                loriotController.sendRemoteCommand(eui, new RemoteCommand(lightValue, 10));
            }
        } catch (Exception e) {
            logger.warn("Command for window cannot {}", lightValue);
            e.printStackTrace();
        }
    }
}
