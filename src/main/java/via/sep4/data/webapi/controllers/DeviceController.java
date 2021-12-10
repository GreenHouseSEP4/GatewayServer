package via.sep4.data.webapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.core.util.Json;
import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.service.device.DeviceService;
import via.sep4.data.webapi.util.ApiKeyUtil;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ApiKeyUtil util;


    @PostMapping
    public ResponseEntity createDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            Device checkDevice = deviceService.findDeviceByEUI(device.getEUI());
            if (!(device.equals(checkDevice))) {
                deviceService.saveDeviceByEUI(device);
                return new ResponseEntity<>(Json.pretty(device), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Device is already registered!", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{eui}")
    public ResponseEntity getDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            return new ResponseEntity<>(device, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity updateDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            deviceService.updateDevice(device);
            return new ResponseEntity<>(Json.pretty(device), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{eui}")
    public ResponseEntity deleteDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            util.checkApi(apiKey);
            deviceService.deleteDeviceByEUI(eui);
            return new ResponseEntity<>("Successfully deleted: " + eui, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
