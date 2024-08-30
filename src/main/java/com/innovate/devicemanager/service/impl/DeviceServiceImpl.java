package com.innovate.devicemanager.service.impl;

import com.innovate.devicemanager.domain.Device;
import com.innovate.devicemanager.repository.DeviceRepository;
import com.innovate.devicemanager.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    /**
     * @param device details which needs to be saved in DB
     * @return device details after saving in DB
     */
    @Override
    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    /**
     * @param id of a device which is requested by user
     * @return details of device whose id is sent in request
     */
    @Override
    public Device getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        return device.orElse(null);
    }

    /**
     * @return list of all devices available in DB
     */
    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    /**
     * @param id of a device which needs to be updated
     * @param device details which needs to be updated
     * @return device details after updating in DB
     */
    @Override
    public Device updateDevice(Long id, Device device) {
        if (deviceRepository.existsById(id)) {
            device.setId(id);
            return deviceRepository.save(device);
        } else {
            return null;
        }
    }

    /**
     * @param id of a device which needs to be updated
     * @param device details which needs to be updated
     * @return device details after updating in DB
     */
    @Override
    public Device partialUpdateDevice(Long id, Device device) {
        Optional<Device> existingDeviceOpt = deviceRepository.findById(id);
        if (existingDeviceOpt.isPresent()) {
            Device existingDevice = existingDeviceOpt.get();
            if (device.getDeviceName() != null) {
                existingDevice.setDeviceName(device.getDeviceName());
            }
            if (device.getDeviceBrand() != null) {
                existingDevice.setDeviceBrand(device.getDeviceBrand());
            }
            return deviceRepository.save(existingDevice);
        } else {
            return null;
        }
    }

    /**
     * @param id of a device which needs to be deleted
     * @return boolean value indicating whether device is deleted or not
     */
    @Override
    public boolean deleteDevice(Long id) {
        if (deviceRepository.existsById(id)) {
            deviceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param brand name by which devices needs to be searched
     * @return list of all devices which belong to the given brand
     */
    @Override
    public List<Device> searchDevicesByBrand(String brand) {
        return deviceRepository.findByDeviceBrand(brand);
    }

}
