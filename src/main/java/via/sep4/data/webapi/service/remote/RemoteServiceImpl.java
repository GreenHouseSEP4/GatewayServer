package via.sep4.data.webapi.service.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import via.sep4.data.webapi.model.loriot.actions.RemoteCommand;
import via.sep4.data.webapi.networking.LoriotController;

@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private LoriotController loriotController;
    
    @Override
    public void sendCommand(String command) {
        String deviceCommand = "000";
        switch(command) {
            case "activate":
                deviceCommand += "10";
                break;
            case "deactivate":
                deviceCommand += "00";
                break;
            default:
                deviceCommand += "01";
        }
        
        System.err.println(deviceCommand);

        try {
            loriotController.send(new RemoteCommand(deviceCommand, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
