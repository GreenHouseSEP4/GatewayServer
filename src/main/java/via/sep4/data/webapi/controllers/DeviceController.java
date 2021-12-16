package via.sep4.data.webapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import via.sep4.data.webapi.model.Device;
import via.sep4.data.webapi.service.device.DeviceService;
import via.sep4.data.webapi.util.ApiKeyUtil;
import via.sep4.data.webapi.util.Constants;

@RestController
@RequestMapping(path = "/devices")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ApiKeyUtil util;

    @Operation(summary = "Register a device")
    @PostMapping(produces = "application/json")
    public ResponseEntity createDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            Device checkDevice = deviceService.findDeviceByEUI(device.getEUI());
            if (checkDevice == null) {
                deviceService.saveDeviceByEUI(device);
                logger.info("Device created: {}", device);
                return new ResponseEntity<>(Json.pretty(device), HttpStatus.CREATED);
            } else {
                logger.error("Device already exists: {}", device);
                return new ResponseEntity<>("Device is already registered!", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not created!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a device by EUI")
    @GetMapping(path ="/{eui}", produces = "application/json")
    public ResponseEntity getDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            if (eui.length() != Constants.EUI_LENGTH) {
                return new ResponseEntity<>("Device eui needs to be 16 digits", HttpStatus.BAD_REQUEST);
            }
            util.checkApi(apiKey);
            Device device = deviceService.findDeviceByEUI(eui);
            if (device != null) {
                logger.info("Device found: {}", device);
                return new ResponseEntity<>(Json.pretty(device), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Device is not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.warn("Bad request");
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update an existing device")
    @PutMapping(produces = "application/json")
    public ResponseEntity updateDevice(@RequestHeader("api-key") String apiKey, @RequestBody Device device) {
        try {
            util.checkApi(apiKey);
            Device deviceFound = deviceService.findDeviceByEUI(device.getEUI());
            if (deviceFound != null) {
                Device deviceUpdated = deviceService.updateDevice(device);
                logger.info("Device updated: {}", device);
                return new ResponseEntity<>(Json.pretty(deviceUpdated), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find device with eui", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not updated!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete an existing device")
    @DeleteMapping(path = "/{eui}", produces = "application/json")
    public ResponseEntity deleteDevice(@RequestHeader("api-key") String apiKey, @PathVariable String eui) {
        try {
            util.checkApi(apiKey);
            Device deviceFound = deviceService.findDeviceByEUI(eui);
            if (deviceFound != null) {
                Device deviceDeleted = deviceService.deleteDeviceByEUI(eui);
                logger.info("Device deleted: {}", deviceService.findDeviceByEUI(eui));
                return new ResponseEntity<>(Json.pretty(deviceDeleted) + eui, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find device with eui", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.warn("Bad request, device not found!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
