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
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo veiculo")
    private String veiculo;

    @NotNull(message = "Marca deve ser informada")
    @NotBlank(message = "Marca deve ser informada")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo marca")
    private String marca;

    @NotNull(message = "Ano deve ser informado")
    private Integer ano;

    @NotNull(message = "Descricao deve ser informado")
    @NotBlank(message = "Descricao deve ser informada")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo descricao")
    private String descricao;

    private boolean vendido;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime created;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updated;

    @NotNull(message = "Chassi deve ser informado")
    @NotBlank(message = "Chassi deve ser informado")
    @Pattern(regexp = "^[^\\/:*?\\\"<>|]+$", message = "Caracter invalido inserido no campo chassi")
    private String chassi;

    @NotNull(message = "Preço deve ser informado")
    @PositiveOrZero(message = "O Preço não pode ser negativo")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
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

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
