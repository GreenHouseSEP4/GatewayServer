package via.sep4.data.webapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ApiKeyUtil util;


    @PostMapping
    public ResponseEntity createDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            Device checkDevice = deviceService.findDeviceByEUI(device.getEUI());
            if (checkDevice == null) {
                deviceService.saveDeviceByEUI(device);
                logger.info("Device created: {}", device);
                return new ResponseEntity<>(Json.pretty(device), HttpStatus.OK);
            } else {
                logger.error("Device already exists: {}", device);
                return new ResponseEntity<>("Device is already registered!", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not created!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{eui}")
    public ResponseEntity getDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            if (eui.length() != 16) {
                return new ResponseEntity<>("Device eui needs to be 16 digits", HttpStatus.BAD_REQUEST);
            }
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            logger.info("Device found: {}", device);
            return new ResponseEntity<>(device, HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("Bad request");
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity updateDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            Device deviceFound = deviceService.findDeviceByEUI(device.getEUI());
            if (deviceFound != null) {
                deviceService.updateDevice(deviceFound);
                logger.info("Device updated: {}", device);
                return new ResponseEntity<>(Json.pretty(device), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find device with eui", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not updated!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{eui}")
    public ResponseEntity deleteDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            util.checkApi(apiKey);
            Device deviceFound = deviceService.findDeviceByEUI(eui);
            if (deviceFound != null) {
                deviceService.deleteDeviceByEUI(eui);
                logger.info("Device deleted: {}", deviceService.findDeviceByEUI(eui));
                return new ResponseEntity<>("Successfully deleted: " + eui, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find device with eui", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not found!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
