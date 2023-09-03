package com.tperuch.edstest.mapper;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.dto.VehicleDtoBuilder;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.entity.VehicleEntityBuilder;

public class VehicleMapper {
    public static VehicleEntity mapToEntity(VehicleDto vehicleDto) {
        return VehicleEntityBuilder.builder()
                .vehicle(vehicleDto.getVehicle())
                .brand(vehicleDto.getBrand())
                .chassis(vehicleDto.getChassis())
                .description(vehicleDto.getDescription())
                .price(vehicleDto.getPrice())
                .year(vehicleDto.getYear())
                .sold(vehicleDto.isSold())
                .build();
    }

    public static VehicleDto mapToDto(VehicleEntity vehicleEntity) {
        return VehicleDtoBuilder.builder()
                .id(vehicleEntity.getId())
                .vehicle(vehicleEntity.getVehicle())
                .chassis(vehicleEntity.getChassis())
                .description(vehicleEntity.getDescription())
                .sold(vehicleEntity.isSold())
                .year(vehicleEntity.getYear())
                .brand(vehicleEntity.getBrand())
                .created(vehicleEntity.getCreated())
                .updated(vehicleEntity.getUpdated())
                .price(vehicleEntity.getPrice())
                .build();
    }
}
