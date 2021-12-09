package via.sep4.data.webapi.controllers;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import via.sep4.data.webapi.service.api.ApiService;
import via.sep4.data.webapi.service.remote.RemoteService;
import via.sep4.data.webapi.util.ApiKeyUtil;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;

    @Autowired
   private ApiKeyUtil util;

    @PostMapping
    public ResponseEntity sendRemoteCommand(@RequestHeader("api-key") String apiKey, @RequestParam String command) {
        try {
            util.checkApi(apiKey);
            remoteService.sendCommand(command);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
