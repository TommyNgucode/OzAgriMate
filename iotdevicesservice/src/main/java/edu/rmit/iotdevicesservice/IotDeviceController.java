package edu.rmit.iotdevicesservice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/iot-devices")
public class IotDeviceController {
    IotDeviceServiceLayer iotDeviceServiceLayer;

    public IotDeviceController () {
        iotDeviceServiceLayer = new IotDeviceServiceLayer();
    }

    @GetMapping ("/farms/{farmId}/devices")
    public ResponseEntity<List<IotDevice>> getAllDevicesByFarm (@PathVariable int farmId) {
        if (!iotDeviceServiceLayer.farmExists(farmId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iotDeviceServiceLayer.getAllDevicesByFarm(farmId), HttpStatus.OK);
    }

    @GetMapping ("/devices/{deviceId}")
    public ResponseEntity<IotDevice> getDeviceById (@PathVariable int deviceId) {
        IotDevice iotDevice = iotDeviceServiceLayer.getDeviceById(deviceId);
        if (iotDevice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iotDevice, HttpStatus.OK);
    }

    @PostMapping ("/farms/{farmId}/devices")
    public ResponseEntity<IotDevice> addDevice (@PathVariable int farmId, @RequestBody IotDevice iotDevice) {
        if (!iotDeviceServiceLayer.farmExists(farmId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (iotDevice.getDeviceType() == null || iotDevice.getLocation() == null || iotDevice.getStatus() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (iotDevice.getFarmId() != 0 && iotDevice.getFarmId() != farmId) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iotDeviceServiceLayer.addDevice(farmId, iotDevice), HttpStatus.CREATED);
    }

    @PutMapping ("/devices/{deviceId}")
    public ResponseEntity<IotDevice> updateDevice (@PathVariable int deviceId, @RequestBody IotDevice iotDevice) {
        if (iotDeviceServiceLayer.getDeviceById(deviceId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (iotDevice.getDeviceType() == null || iotDevice.getLocation() == null || iotDevice.getStatus() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iotDeviceServiceLayer.updateDevice(deviceId, iotDevice), HttpStatus.OK);
    }

    @DeleteMapping ("/devices/{deviceId}")
    public ResponseEntity<Void> deleteDevice (@PathVariable int deviceId) {
        if (!iotDeviceServiceLayer.deleteDevice(deviceId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
