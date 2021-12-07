package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import via.sep4.data.webapi.service.remote.RemoteService;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;

    @PostMapping
    public void sendRemoteCommand(@RequestParam String command) {
        remoteService.sendCommand(command);
    }
}
