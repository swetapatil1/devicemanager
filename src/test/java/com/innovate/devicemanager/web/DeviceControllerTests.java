package com.innovate.devicemanager.web;

import com.innovate.devicemanager.domain.Device;
import com.innovate.devicemanager.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DeviceController.class)
public class DeviceControllerTests {

    @MockBean
    private DeviceService deviceService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        DeviceController deviceController = new DeviceController(deviceService);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    void testAddDevice() throws Exception {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceService.saveDevice(any(Device.class))).thenReturn(device);

        mockMvc.perform(post("/api/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"deviceName\": \"iPhone 13\", \"deviceBrand\": \"Apple\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.deviceName").value("iPhone 13"))
                .andExpect(jsonPath("$.deviceBrand").value("Apple"));
    }

    @Test
    void testGetDeviceById() throws Exception {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        device.setId(1L);
        when(deviceService.getDeviceById(anyLong())).thenReturn(device);

        mockMvc.perform(get("/api/devices/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceName").value("iPhone 13"))
                .andExpect(jsonPath("$.deviceBrand").value("Apple"));
    }

    @Test
    void testListAllDevices() throws Exception {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceService.getAllDevices()).thenReturn(Collections.singletonList(device));

        mockMvc.perform(get("/api/devices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviceName").value("iPhone 13"))
                .andExpect(jsonPath("$[0].deviceBrand").value("Apple"));
    }

    @Test
    void testUpdateDevice() throws Exception {
        Device device = new Device("iPhone 14", "Apple", LocalDateTime.now());
        device.setId(1L);
        when(deviceService.updateDevice(anyLong(), any(Device.class))).thenReturn(device);

        mockMvc.perform(put("/api/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"deviceName\": \"iPhone 14\", \"deviceBrand\": \"Apple\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceName").value("iPhone 14"))
                .andExpect(jsonPath("$.deviceBrand").value("Apple"));
    }

    @Test
    void testPartialUpdateDevice() throws Exception {
        Device device = new Device("iPhone 14", "Apple", LocalDateTime.now());
        device.setId(1L);
        when(deviceService.partialUpdateDevice(anyLong(), any(Device.class))).thenReturn(device);

        mockMvc.perform(patch("/api/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"deviceName\": \"iPhone 14\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceName").value("iPhone 14"));
    }

    @Test
    void testDeleteDevice() throws Exception {
        when(deviceService.deleteDevice(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchDevicesByBrand() throws Exception {
        Device device = new Device("iPhone 13", "Apple", LocalDateTime.now());
        when(deviceService.searchDevicesByBrand(any(String.class))).thenReturn(Collections.singletonList(device));

        mockMvc.perform(get("/api/devices/search").param("brand", "Apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviceName").value("iPhone 13"))
                .andExpect(jsonPath("$[0].deviceBrand").value("Apple"));
    }
}