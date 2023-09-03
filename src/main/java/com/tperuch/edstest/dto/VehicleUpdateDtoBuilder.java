package com.tperuch.edstest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class VehicleUpdateDtoBuilder {
    private Long id;
    private @NotNull(message = "Veículo deve ser informado") String vehicle;
    private @NotNull(message = "Marca deve ser informada") String brand;
    private @NotNull(message = "Ano deve ser informado") Integer year;
    private @NotNull(message = "Descricao deve ser informado") String description;
    private boolean sold;
    private LocalDateTime created;
    private @NotNull(message = "Chassi deve ser informado") String chassis;
    private @NotNull(message = "Preço deve ser informado") @PositiveOrZero(message = "O valor não pode ser negativo") BigDecimal price;
    private LocalDateTime updated;

    private VehicleUpdateDtoBuilder() {
    }

    public static VehicleUpdateDtoBuilder builder() {
        return new VehicleUpdateDtoBuilder();
    }

    public VehicleUpdateDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public VehicleUpdateDtoBuilder vehicle(String vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public VehicleUpdateDtoBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleUpdateDtoBuilder year(Integer year) {
        this.year = year;
        return this;
    }

    public VehicleUpdateDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public VehicleUpdateDtoBuilder sold(boolean sold) {
        this.sold = sold;
        return this;
    }

    public VehicleUpdateDtoBuilder created(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public VehicleUpdateDtoBuilder chassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public VehicleUpdateDtoBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleUpdateDtoBuilder updated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public VehicleUpdateDto build() {
        VehicleUpdateDto vehicleUpdateDto = new VehicleUpdateDto();
        vehicleUpdateDto.setId(id);
        vehicleUpdateDto.setVehicle(vehicle);
        vehicleUpdateDto.setBrand(brand);
        vehicleUpdateDto.setYear(year);
        vehicleUpdateDto.setDescription(description);
        vehicleUpdateDto.setSold(sold);
        vehicleUpdateDto.setCreated(created);
        vehicleUpdateDto.setChassis(chassis);
        vehicleUpdateDto.setPrice(price);
        vehicleUpdateDto.setUpdated(updated);
        return vehicleUpdateDto;
    }
}
