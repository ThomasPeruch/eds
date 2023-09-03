package com.tperuch.edstest.stub;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.dto.VehicleDtoBuilder;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.entity.VehicleEntityBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleEntityStub {
    public static VehicleEntity getStub(){
        return VehicleEntityBuilder.builder()
                .vehicle("vehicle")
                .brand("brand")
                .chassis("chassis")
                .description("description")
                .year(2015)
                .price(BigDecimal.valueOf(50000))
                .created(LocalDateTime.now())
                .build();
    }
}
