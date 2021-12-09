package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import via.sep4.data.webapi.service.api.ApiService;
import via.sep4.data.webapi.service.remote.RemoteService;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private ApiService apiService;

    @PostMapping
    public void sendRemoteCommand(@RequestHeader("api-key") String apiKey, @RequestParam String command) {
        if (!(apiKey.equals("")) && apiKey.equals(getKey())) {
            remoteService.sendCommand(command);
        } else {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

   private String getKey() {
        String key = "";
        try {
            key = apiService.findById(1);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}
