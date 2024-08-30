package com.innovate.devicemanager.web;

import com.innovate.devicemanager.domain.Device;
import com.innovate.devicemanager.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * @param device details to be saved
     * @return device details after saving in DB
     */
    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        device.setCreationTime(LocalDateTime.now());
        Device savedDevice = deviceService.saveDevice(device);
        return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
    }

    /**
     * @param id of a device which is requested by user
     * @return details of device whose id is sent in request
     */
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        if (device != null) {
            return new ResponseEntity<>(device, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @return list of all devices available in DB
     */
    @GetMapping
    public ResponseEntity<List<Device>> listAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    /**
     * @param id of a device which needs to be updated
     * @param device details which needs to be updated
     * @return device details after updating in DB
     */
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        Device updatedDevice = deviceService.updateDevice(id, device);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @param id of a device which needs to be updated
     * @param device details which needs to be updated
     * @return device details after updating in DB
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Device> partialUpdateDevice(@PathVariable Long id, @RequestBody Device device) {
        Device updatedDevice = deviceService.partialUpdateDevice(id, device);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @param id of a device which needs to be deleted
     * @return empty response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        boolean isDeleted = deviceService.deleteDevice(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * @param brand name by which devices needs to be searched
     * @return list of all devices which belong to the given brand
     */
    @GetMapping("/search")
    public ResponseEntity<List<Device>> searchDevicesByBrand(@RequestParam String brand) {
        List<Device> devices = deviceService.searchDevicesByBrand(brand);
        if (!devices.isEmpty()) {
            return new ResponseEntity<>(devices, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
