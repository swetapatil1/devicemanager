package com.innovate.devicemanager.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceName;

    @Column(nullable = false)
    private String deviceBrand;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Device(String deviceName, String deviceBrand, LocalDateTime creationTime) {
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
        this.creationTime = creationTime;
    }

    public Device() {

    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
