package via.sep4.data.webapi.service.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.loriot.actions.RemoteCommand;
import via.sep4.data.webapi.networking.LoriotController;
import via.sep4.data.webapi.util.Constants;

@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private LoriotController loriotController;
    
    @Override
    public void sendCommand(String command) {
        String deviceCommand = "000";
        switch(command) {
            case Constants.ACTIVATE_COMMAND:
                deviceCommand += "10";
                break;
            case Constants.DEACTIVATE_COMMAND:
                deviceCommand += "00";
                break;
            default:
                deviceCommand += "01";
        }
        
        System.err.println(deviceCommand);

        try {
            loriotController.send(new RemoteCommand(deviceCommand, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
