package com.tperuch.edstest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleDto {
    private Long id;

    @NotNull(message = "Veículo deve ser informado")
    @NotBlank(message = "Veículo deve ser informado")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo vehicle/veiculo")
    private String vehicle;

    @NotNull(message = "Marca deve ser informada")
    @NotBlank(message = "Marca deve ser informada")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo brand/marca")
    private String brand;

    @NotNull(message = "Ano deve ser informado")
    private Integer year;

    @NotNull(message = "Descricao deve ser informado")
    @NotBlank(message = "Descricao deve ser informada")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo description/descricao")
    private String description;

    private boolean sold;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime created;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updated;

    @NotNull(message = "Chassi deve ser informado")
    @NotBlank(message = "Chassi deve ser informado")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo brand/marca")
    private String chassis;

    @NotNull(message = "Preço deve ser informado")
    @PositiveOrZero(message = "O Preço não pode ser negativo")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
