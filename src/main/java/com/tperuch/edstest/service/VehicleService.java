package com.tperuch.edstest.service;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.exception.AlreadyInUseException;
import com.tperuch.edstest.mapper.VehicleMapper;
import com.tperuch.edstest.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public VehicleDto saveVehicle(VehicleDto vehicleDto){
        if(chassisExists(vehicleDto.getChassis())){
            throw new AlreadyInUseException("Chassi já registrado, favor escolher outro");
        }
        if(IsYearInvalid(vehicleDto.getYear())){
            throw new IllegalArgumentException("O Ano do veículo não pode ser posterior ao ano atual");
        }
        VehicleEntity vehicleEntity = VehicleMapper.mapToEntity(vehicleDto);
        vehicleEntity.setCreated(LocalDateTime.now());
        return VehicleMapper.mapToDto(repository.save(vehicleEntity));

    }

    private boolean IsYearInvalid(Integer year) {
        int currentYear = LocalDateTime.now().getYear();
        int result = year.compareTo(currentYear);
        return result > 0;
    }

    private boolean chassisExists(String chassis) {
        return repository.existsByChassis(chassis);
    }

}
