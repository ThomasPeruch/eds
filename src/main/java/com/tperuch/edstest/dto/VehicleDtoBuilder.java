package com.tperuch.edstest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class VehicleDtoBuilder {
    private Long id;
    private @NotNull(message = "Veículo deve ser informado") String vehicle;
    private @NotNull(message = "Marca deve ser informada") String brand;
    private @NotNull(message = "Ano deve ser informado") Integer year;
    private @NotNull(message = "Descricao deve ser informado") String description;
    private boolean sold;
    private LocalDateTime created;
    private LocalDateTime updated;
    private @NotNull(message = "Chassi deve ser informado") String chassis;
    private @NotNull(message = "Preço deve ser informado") @PositiveOrZero(message = "O valor não pode ser negativo") BigDecimal price;

    private VehicleDtoBuilder() {
    }

    public static VehicleDtoBuilder builder() {
        return new VehicleDtoBuilder();
    }

    public VehicleDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public VehicleDtoBuilder vehicle(String vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public VehicleDtoBuilder brand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleDtoBuilder year(Integer year) {
        this.year = year;
        return this;
    }

    public VehicleDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public VehicleDtoBuilder sold(boolean sold) {
        this.sold = sold;
        return this;
    }

    public VehicleDtoBuilder created(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public VehicleDtoBuilder updated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public VehicleDtoBuilder chassis(String chassis) {
        this.chassis = chassis;
        return this;
    }

    public VehicleDtoBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleDto build() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(id);
        vehicleDto.setVeiculo(vehicle);
        vehicleDto.setMarca(brand);
        vehicleDto.setAno(year);
        vehicleDto.setDescricao(description);
        vehicleDto.setVendido(sold);
        vehicleDto.setCreated(created);
        vehicleDto.setUpdated(updated);
        vehicleDto.setChassi(chassis);
        vehicleDto.setPreco(price);
        return vehicleDto;
    }
}
