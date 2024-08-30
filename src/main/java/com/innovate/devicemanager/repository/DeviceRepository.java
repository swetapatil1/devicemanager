package com.innovate.devicemanager.repository;

import com.innovate.devicemanager.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByDeviceBrand(String brand);
}
