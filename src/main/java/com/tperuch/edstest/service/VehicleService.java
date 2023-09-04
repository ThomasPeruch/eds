package com.tperuch.edstest.service;

import com.tperuch.edstest.dto.SearchVehicleCriteria;
import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.mapper.VehicleMapper;
import com.tperuch.edstest.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private VehicleRepository repository;

    public VehicleDto saveVehicle(VehicleDto vehicleDto) {
        logger.info("Iniciando processo de cadastro de veiculo - VEICULO = {}",vehicleDto);
        verifyYear(vehicleDto.getAno());
        verifyChassis(vehicleDto.getChassi());
        VehicleEntity vehicleEntity = VehicleMapper.mapToEntity(vehicleDto);
        vehicleEntity.setCreated(LocalDateTime.now());
        logger.info("Salvando veiculo na base de dados");
        return VehicleMapper.mapToDto(repository.save(vehicleEntity));
    }

    public Page<VehicleDto> findAll(PageRequest pageRequest) {
        logger.info("Buscando todos os veiculos na base de dados");
        Page<VehicleEntity> vehicles = repository.findAll(pageRequest);
        return vehicles.map(VehicleMapper::mapToDto);
    }

    public VehicleDto findVehicleById(Long id) {
        VehicleEntity vehicleEntity = getVehicleEntity(id);
        return VehicleMapper.mapToDto(vehicleEntity);
    }

    public Page<VehicleDto> findAllSpecificated(PageRequest pageRequest, SearchVehicleCriteria searchVehicleCriteria) {
        logger.info("Buscando veiculos de acordo com filtro. {} {} {}",
                searchVehicleCriteria.getField(), searchVehicleCriteria.getOperator(), searchVehicleCriteria.getValue());
        Page<VehicleEntity> vehicles = repository.findAll(searchVehicleCriteria, pageRequest);
        return vehicles.map(VehicleMapper::mapToDto);
    }

    public VehicleDto updateVehicleById(Long id, VehicleDto vehicleDto) {
        logger.info("Iniciando edicao/atualizacao de veiculo - id {}", id);
        VehicleEntity vehicle = getVehicleEntity(id);
        verifyYear(vehicleDto.getAno());
        if (!isSameChassis(vehicle.getChassis(), vehicleDto.getChassi())) {
            verifyChassis(vehicleDto.getChassi());
        }
        VehicleEntity entityToUpdate = mapToUpdate(vehicle, vehicleDto);
        logger.info("Salvando veiculo atualizado - VEICULO: {}", entityToUpdate);
        return VehicleMapper.mapToDto(repository.save(entityToUpdate));
    }

    public void deleteVehicleById(Long id) {
        logger.info("Iniciando remocao de veiculo de id {}",id);
        repository.delete(getVehicleEntity(id));
    }

    private void verifyChassis(String chassis) {
        if (repository.existsByChassis(chassis)) {
            logger.error("Chassi ja registrado, favor escolher outro");
            throw new IllegalArgumentException("Chassi ja registrado, favor escolher outro");
        }
    }

    private void verifyYear(Integer year) {
        if (IsYearInvalid(year)) {
            logger.error("O Ano do veiculo nao pode ser posterior ao ano atual. Ano inserido = {}", year);
            throw new IllegalArgumentException("O Ano do veiculo nao pode ser posterior ao ano atual");
        }
    }

    private VehicleEntity getVehicleEntity(Long id) {
        logger.info("Buscando veiculo por ID - {}",id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Nao foi encontrado veiculo com o ID informado - {}",id);
                    return new EntityNotFoundException("Nao foi encontrado veiculo com o ID informado - " + id);
                });
    }

    private boolean isSameChassis(String entityChassis, String chassisFromRequest) {
        return entityChassis.equalsIgnoreCase(chassisFromRequest);
    }

    private VehicleEntity mapToUpdate(VehicleEntity entityFromDatabase, VehicleDto vehicleDto) {
        VehicleEntity entityToUpdate = entityFromDatabase;
        entityToUpdate.setVehicle(vehicleDto.getVeiculo());
        entityToUpdate.setSold(vehicleDto.isVendido());
        entityToUpdate.setYear(vehicleDto.getAno());
        entityToUpdate.setDescription(vehicleDto.getDescricao());
        entityToUpdate.setBrand(vehicleDto.getMarca());
        entityToUpdate.setChassis(vehicleDto.getChassi());
        entityToUpdate.setPrice(vehicleDto.getPreco());
        entityToUpdate.setUpdated(LocalDateTime.now());
        return entityToUpdate;
    }


    private boolean IsYearInvalid(Integer year) {
        int currentYear = LocalDateTime.now().getYear();
        int result = year.compareTo(currentYear);
        return result > 0;
    }
}
