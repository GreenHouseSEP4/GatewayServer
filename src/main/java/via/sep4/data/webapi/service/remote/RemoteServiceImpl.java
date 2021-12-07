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
        String deviceCommand = "";
        switch(command) {
            case "activate":
                deviceCommand = "1";
            case "deactivate":
                deviceCommand= "0";
            default:
                deviceCommand = "2";
        }
        
        System.err.println(deviceCommand);
        try {
            loriotController.send(new RemoteCommand(deviceCommand, 2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
