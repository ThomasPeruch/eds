package com.tperuch.edstest.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class VehicleEntityBuilder {
    private Long id;
    private String vehicle;
    private String brand;
    private Integer year;
    private String description;
    private boolean sold;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String chassis;
    private BigDecimal price;

    private VehicleEntityBuilder() {
    }

    public static VehicleEntityBuilder builder() {
        return new VehicleEntityBuilder();
    }

    public VehicleEntityBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public VehicleEntityBuilder vehicle(String vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public VehicleEntityBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleEntityBuilder year(Integer year) {
        this.year = year;
        return this;
    }

    public VehicleEntityBuilder description(String description) {
        this.description = description;
        return this;
    }

    public VehicleEntityBuilder sold(boolean sold) {
        this.sold = sold;
        return this;
    }

    public VehicleEntityBuilder created(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public VehicleEntityBuilder updated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public VehicleEntityBuilder chassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public VehicleEntityBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleEntity build() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(id);
        vehicleEntity.setVehicle(vehicle);
        vehicleEntity.setBrand(brand);
        vehicleEntity.setYear(year);
        vehicleEntity.setDescription(description);
        vehicleEntity.setSold(sold);
        vehicleEntity.setCreated(created);
        vehicleEntity.setUpdated(updated);
        vehicleEntity.setChassis(chassis);
        vehicleEntity.setPrice(price);
        return vehicleEntity;
    }
}
