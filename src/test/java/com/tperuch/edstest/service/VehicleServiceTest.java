package com.tperuch.edstest.service;

import com.tperuch.edstest.dto.SearchVehicleCriteria;
import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.entity.VehicleEntity;
import com.tperuch.edstest.repository.VehicleRepository;
import com.tperuch.edstest.stub.PageRequestStub;
import com.tperuch.edstest.stub.SearchVehicleCriteriaStub;
import com.tperuch.edstest.stub.VehicleDtoStub;
import com.tperuch.edstest.stub.VehicleEntityStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {
    @Mock
    private VehicleRepository repository;

    @InjectMocks
    private VehicleService service;

    @Test
    void saveVehicle() {
        VehicleDto dto = VehicleDtoStub.getStub();

        when(repository.existsByChassis(dto.getChassi())).thenReturn(false);
        when(repository.save(any())).thenReturn(VehicleEntityStub.getStub());

        VehicleDto vehicleDto = service.saveVehicle(dto);

        assertTrue(Objects.nonNull(vehicleDto.getCreated()));
    }

    @Test
    void shouldNotSaveBecauseYearIsInvalid() {
        VehicleDto dto = VehicleDtoStub.getStub();
        dto.setAno(2025);

        Exception exception = assertThrows(Exception.class, () -> service.saveVehicle(dto));

        assertEquals("O Ano do veiculo nao pode ser posterior ao ano atual", exception.getMessage());
        assertEquals(exception.getClass().getName(), IllegalArgumentException.class.getName());
    }

    @Test
    void shouldNotSaveBecauseChassisAlreadyExists() {
        VehicleDto dto = VehicleDtoStub.getStub();

        when(repository.existsByChassis(dto.getChassi())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> service.saveVehicle(dto));

        assertEquals("Chassi ja registrado, favor escolher outro", exception.getMessage());
        assertEquals(exception.getClass().getName(), IllegalArgumentException.class.getName());
    }

    @Test
    void findAll() {
        PageRequest pageRequest = PageRequestStub.getStub();

        when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(VehicleEntityStub.getStub())));

        service.findAll(pageRequest);

        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void findVehicleById() {
        VehicleEntity vehicleEntity = VehicleEntityStub.getStub();
        vehicleEntity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(vehicleEntity));

        service.findVehicleById(1L);

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findAllSpecificated() {
        PageRequest pageRequest = PageRequestStub.getStub();
        SearchVehicleCriteria searchVehicleCriteria = SearchVehicleCriteriaStub.getStub();

        when(repository.findAll(searchVehicleCriteria, pageRequest))
                .thenReturn(new PageImpl<>(List.of(VehicleEntityStub.getStub())));

        service.findAllSpecificated(pageRequest, searchVehicleCriteria);

        verify(repository, times(1)).findAll(searchVehicleCriteria, pageRequest);
    }

    @Test
    void updateVehicleById() {
        VehicleDto dto = VehicleDtoStub.getStub();
        VehicleEntity vehicleEntity = VehicleEntityStub.getStub();
        vehicleEntity.setId(1L);

        VehicleEntity updatedEntity = vehicleEntity;
        updatedEntity.setUpdated(LocalDateTime.now());

        when(repository.findById(1L)).thenReturn(Optional.of(vehicleEntity));
        when(repository.save(vehicleEntity)).thenReturn(updatedEntity);

        service.updateVehicleById(1L, dto);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(vehicleEntity);
    }

    @Test
    void shouldNotUpdateVehicleByIdWhenChassisIsInvalid() {
        VehicleDto dto = VehicleDtoStub.getStub();
        VehicleEntity vehicleEntity = VehicleEntityStub.getStub();
        vehicleEntity.setId(1L);
        vehicleEntity.setChassis("abc");

        VehicleEntity updatedEntity = vehicleEntity;
        updatedEntity.setUpdated(LocalDateTime.now());

        when(repository.findById(1L)).thenReturn(Optional.of(vehicleEntity));
        when(repository.existsByChassis(anyString())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> service.updateVehicleById(1L, dto));

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).existsByChassis(anyString());
        assertEquals("Chassi ja registrado, favor escolher outro", exception.getMessage());
        assertEquals(exception.getClass().getName(), IllegalArgumentException.class.getName());
    }

    @Test
    void deleteVehicleById() {
        VehicleEntity vehicleEntity = VehicleEntityStub.getStub();
        when(repository.findById(1L)).thenReturn(Optional.of(vehicleEntity));
        doNothing().when(repository).delete(vehicleEntity);

        service.deleteVehicleById(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository,times(1)).delete(vehicleEntity);
    }
}