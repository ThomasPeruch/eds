package com.tperuch.edstest.service;

import com.tperuch.edstest.dto.SearchVehicleCriteria;
import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.exception.AlreadyInUseException;
import com.tperuch.edstest.mapper.VehicleMapper;
import com.tperuch.edstest.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public VehicleDto saveVehicle(VehicleDto vehicleDto) {
        verifyYear(vehicleDto.getYear());
        verifyChassis(vehicleDto.getChassis());
        VehicleEntity vehicleEntity = VehicleMapper.mapToEntity(vehicleDto);
        vehicleEntity.setCreated(LocalDateTime.now());
        return VehicleMapper.mapToDto(repository.save(vehicleEntity));
    }

    public Page<VehicleDto> findAll(PageRequest pageRequest) {
        Page<VehicleEntity> vehicles = repository.findAll(pageRequest);
        return vehicles.map(VehicleMapper::mapToDto);

    }

    public VehicleDto findVehicleById(Long id) {
        VehicleEntity vehicleEntity = getVehicleEntity(id);
        return VehicleMapper.mapToDto(vehicleEntity);
    }

    public Page<VehicleDto> findAllSpecificated(PageRequest pageRequest, SearchVehicleCriteria searchVehicleCriteria) {
        Page<VehicleEntity> vehicles = repository.findAll(searchVehicleCriteria, pageRequest);
        return vehicles.map(VehicleMapper::mapToDto);
    }

    public VehicleDto updateVehicleById(Long id, VehicleDto vehicleDto) {
        VehicleEntity mappedEntity = getVehicleEntity(id);
        verifyYear(vehicleDto.getYear());
        if (!isSameChassis(mappedEntity.getChassis(), vehicleDto.getChassis())) {
            verifyChassis(vehicleDto.getChassis());
        }
        return VehicleMapper.mapToDto(repository.save(mapToUpdate(mappedEntity, vehicleDto)));
    }

    private void verifyChassis(String chassis) {
        if (repository.existsByChassis(chassis)) {
            throw new AlreadyInUseException("Chassi já registrado, favor escolher outro");
        }
    }

    private void verifyYear(Integer year) {
        if (IsYearInvalid(year)) {
            throw new IllegalArgumentException("O Ano do veículo não pode ser posterior ao ano atual");
        }
    }

    private VehicleEntity getVehicleEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado veículo com o ID informado - " + id));
    }

    private boolean isSameChassis(String entityChassis, String chassisFromRequest) {
        return entityChassis.equalsIgnoreCase(chassisFromRequest);
    }

    private VehicleEntity mapToUpdate(VehicleEntity entityFromDatabase, VehicleDto vehicleDto) {
        VehicleEntity entityToUpdate = entityFromDatabase;
        entityToUpdate.setVehicle(vehicleDto.getVehicle());
        entityToUpdate.setSold(vehicleDto.isSold());
        entityToUpdate.setYear(vehicleDto.getYear());
        entityToUpdate.setDescription(vehicleDto.getDescription());
        entityToUpdate.setBrand(vehicleDto.getBrand());
        entityToUpdate.setChassis(vehicleDto.getChassis());
        entityToUpdate.setPrice(vehicleDto.getPrice());
        entityToUpdate.setUpdated(LocalDateTime.now());
        return entityToUpdate;
    }


    private boolean IsYearInvalid(Integer year) {
        int currentYear = LocalDateTime.now().getYear();
        int result = year.compareTo(currentYear);
        return result > 0;
    }
}
