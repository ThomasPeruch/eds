package com.tperuch.edstest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.service.VehicleService;
import com.tperuch.edstest.stub.PageRequestStub;
import com.tperuch.edstest.stub.VehicleDtoStub;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VehicleService vehicleService;

    private final String baseUrl = "http://localhost:8080/veiculos";

    @Test
    void saveVehicle() throws Exception {
        VehicleDto vehicleDto = VehicleDtoStub.getStub();
        VehicleDto savedVehicle = vehicleDto;
        savedVehicle.setId(1L);

        String json = objectToJson(vehicleDto);

        when(vehicleService.saveVehicle(any())).thenReturn(savedVehicle);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("\"id\":" + savedVehicle.getId()));
    }

    @Test
    void shouldNotSaveVehicleWhenChassisAlreadyExists() throws Exception {
        VehicleDto vehicleDto = VehicleDtoStub.getStub();

        String json = objectToJson(vehicleDto);

        when(vehicleService.saveVehicle(any())).thenThrow(new IllegalArgumentException("Chassi ja registrado, favor escolher outro"));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof IllegalArgumentException))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Chassi ja registrado, favor escolher outro"));
    }

    @Test
    void shouldNotSaveVehicleWhenYearIsInvalid() throws Exception {
        VehicleDto vehicleDto = VehicleDtoStub.getStub();
        vehicleDto.setYear(2025);

        String json = objectToJson(vehicleDto);

        when(vehicleService.saveVehicle(any())).thenThrow(new IllegalArgumentException("O Ano do veiculo nao pode ser posterior ao ano atual"));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof IllegalArgumentException))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("O Ano do veiculo nao pode ser posterior ao ano atual"));
    }

    @Test
    void getPaginatedVehicles() throws Exception {
        VehicleDto vehicleDto = VehicleDtoStub.getStub();
        vehicleDto.setId(1L);
        vehicleDto.setCreated(LocalDateTime.now());

        when(vehicleService.findAll(any())).thenReturn(new PageImpl<>(List.of(vehicleDto)));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("\"id\":" + vehicleDto.getId()));
    }

    @Test
    void getPaginatedAndFilteredVehicles() throws Exception {
        VehicleDto vehicleDto = VehicleDtoStub.getStub();
        vehicleDto.setId(1L);
        vehicleDto.setCreated(LocalDateTime.now());

        when(vehicleService.findAllSpecificated(any(), any())).thenReturn(new PageImpl<>(List.of(vehicleDto)));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(baseUrl.concat("/search"))
                        .param("field","brand")
                        .param("operator","=")
                        .param("value","brand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("\"id\":" + vehicleDto.getId()));
    }

    @Test
    void getVehicleById() throws Exception {
        Long id = 1L;
        VehicleDto vehicleDto = VehicleDtoStub.getStub();
        vehicleDto.setId(id);
        vehicleDto.setCreated(LocalDateTime.now());

        when(vehicleService.findVehicleById(id)).thenReturn(vehicleDto);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(baseUrl.concat("/"+id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("\"id\":" + vehicleDto.getId()));
    }

    @Test
    void shouldNotGetVehicleByIdWhenVehicleDontExists() throws Exception {
        Long id = 8L;

        when(vehicleService.findVehicleById(id)).thenThrow(new EntityNotFoundException("Nao foi encontrado veiculo com o ID informado - "+id));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(baseUrl.concat("/"+id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(exception -> assertTrue(exception.getResolvedException() instanceof EntityNotFoundException))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("Nao foi encontrado veiculo com o ID informado - " + id));
    }

    @Test
    void updateVehicleById() throws Exception {
        VehicleDto vehicle = VehicleDtoStub.getStub();
        VehicleDto updated = VehicleDtoStub.getUpdateStub();

        when(vehicleService.updateVehicleById(any(), any())).thenReturn(updated);

        String json = objectToJson(vehicle);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(baseUrl.concat("/"+1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains("\"id\":" + updated.getId()));
        assertFalse(content.contains("\"update\":null"));
    }

    @Test
    void deleteVehicleById() throws Exception {
        doNothing().when(vehicleService).deleteVehicleById(1L);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(baseUrl.concat("/"+1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertTrue(content.isEmpty());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}