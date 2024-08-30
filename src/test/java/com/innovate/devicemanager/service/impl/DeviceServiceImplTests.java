package com.innovate.devicemanager.service.impl;

import com.innovate.devicemanager.domain.Device;
import com.innovate.devicemanager.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DeviceServiceImplTests {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDevice() {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device savedDevice = deviceService.saveDevice(device);
        assertNotNull(savedDevice);
        assertEquals("iPhone 13", savedDevice.getDeviceName());
        assertEquals("Apple", savedDevice.getDeviceBrand());
    }

    @Test
    void testGetDeviceById() {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        device.setId(1L);
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(device));

        Device fetchedDevice = deviceService.getDeviceById(1L);
        assertNotNull(fetchedDevice);
        assertEquals("iPhone 13", fetchedDevice.getDeviceName());
    }

    @Test
    void testGetAllDevices() {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceRepository.findAll()).thenReturn(Collections.singletonList(device));

        List<Device> devices = deviceService.getAllDevices();
        assertFalse(devices.isEmpty());
        assertEquals("iPhone 13", devices.get(0).getDeviceName());
    }

    @Test
    void testUpdateDevice() {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        device.setId(1L);
        when(deviceRepository.existsById(anyLong())).thenReturn(true);
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device updatedDevice = deviceService.updateDevice(1L, device);
        assertNotNull(updatedDevice);
        assertEquals("iPhone 13", updatedDevice.getDeviceName());
    }

    @Test
    void testPartialUpdateDevice() {
        Device existingDevice = new Device("iPhone 13", "Apple", LocalDateTime.now());
        existingDevice.setId(1L);
        Device updatedDevice = new Device("iPhone 14", null, null);
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(existingDevice);

        Device result = deviceService.partialUpdateDevice(1L, updatedDevice);
        assertNotNull(result);
        assertEquals("iPhone 14", result.getDeviceName());
    }

    @Test
    void testDeleteDevice() {
        when(deviceRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(deviceRepository).deleteById(anyLong());

        boolean isDeleted = deviceService.deleteDevice(1L);
        assertTrue(isDeleted);
        verify(deviceRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchDevicesByBrand() {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceRepository.findByDeviceBrand(any(String.class))).thenReturn(Collections.singletonList(device));

        List<Device> devices = deviceService.searchDevicesByBrand("Apple");
        assertFalse(devices.isEmpty());
        assertEquals("iPhone 13", devices.get(0).getDeviceName());
    }
}