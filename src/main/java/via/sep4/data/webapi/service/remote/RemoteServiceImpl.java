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
    public void setWindow(String eui, int commandPercentage) {
        try {
            loriotController.send(eui, new RemoteCommand(commandPercentage, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
