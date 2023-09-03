package com.tperuch.edstest.stub;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.dto.VehicleDtoBuilder;

import java.math.BigDecimal;

public class VehicleDtoStub {
    public static VehicleDto getStub(){
        return VehicleDtoBuilder.builder()
                .vehicle("vehicle")
                .brand("brand")
                .chassis("chassis")
                .description("description")
                .year(2015)
                .price(BigDecimal.valueOf(50000))
                .build();
    }
}
