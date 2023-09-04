package com.tperuch.edstest.mapper;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.dto.VehicleDtoBuilder;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.entity.VehicleEntityBuilder;

public class VehicleMapper {
    public static VehicleEntity mapToEntity(VehicleDto vehicleDto) {
        return VehicleEntityBuilder.builder()
                .vehicle(vehicleDto.getVeiculo())
                .brand(vehicleDto.getMarca())
                .chassis(vehicleDto.getChassi())
                .description(vehicleDto.getDescricao())
                .price(vehicleDto.getPreco())
                .year(vehicleDto.getAno())
                .sold(vehicleDto.isVendido())
                .build();
    }

    public static VehicleDto mapToDto(VehicleEntity vehicleEntity) {
        return VehicleDtoBuilder.builder()
                .id(vehicleEntity.getId())
                .vehicle(vehicleEntity.getVeiculo())
                .chassis(vehicleEntity.getChassi())
                .description(vehicleEntity.getDescricao())
                .sold(vehicleEntity.isVendido())
                .year(vehicleEntity.getAno())
                .brand(vehicleEntity.getMarca())
                .created(vehicleEntity.getCreated())
                .updated(vehicleEntity.getUpdated())
                .price(vehicleEntity.getPreco())
                .build();
    }
}
