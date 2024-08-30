package com.innovate.devicemanager.service;

import com.innovate.devicemanager.domain.Device;

import java.util.List;

public interface DeviceService {

    Device saveDevice(Device device);

    Device getDeviceById(Long id);

    List<Device> getAllDevices();

    Device updateDevice(Long id, Device device);

    Device partialUpdateDevice(Long id, Device device);

    boolean deleteDevice(Long id);

    List<Device> searchDevicesByBrand(String brand);
}
