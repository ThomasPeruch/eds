package com.tperuch.edstest.controller;

import com.tperuch.edstest.dto.SearchVehicleCriteria;
import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "veiculos")
@Tag(name = "Veículos", description = "Operações relacionadas a veículos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Registra um novo veículo")
    @PostMapping
    public ResponseEntity<VehicleDto> saveVehicle(@RequestBody @Valid VehicleDto vehicleDto){
        return new ResponseEntity(vehicleService.saveVehicle(vehicleDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Busca paginada de veículos, por padrão aparecerão 5 veículos por página")
    @GetMapping
    public ResponseEntity<Page<VehicleDto>> getPaginatedVehicles(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        return new ResponseEntity(vehicleService.findAll(pageRequest), HttpStatus.OK);
    }

    @Operation(summary = "Busca por veículo especifico, usar filtros para busca")
    @GetMapping(value = "/search")
    public ResponseEntity<Page<VehicleDto>> getPaginatedAndFilteredVehicles(
            @Valid SearchVehicleCriteria searchVehicleCriteria,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage

    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        return new ResponseEntity(vehicleService.findAllSpecificated(pageRequest,searchVehicleCriteria), HttpStatus.OK);
    }

    @Operation(summary = "Busca veículo por Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable Long id){
        return new ResponseEntity(vehicleService.findVehicleById(id), HttpStatus.OK);
    }

    @Operation(summary = "Edita veículo por Id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleDto> updateVehicleById(@PathVariable Long id, @RequestBody @Valid VehicleDto vehicleDto){
        return new ResponseEntity(vehicleService.updateVehicleById(id,vehicleDto), HttpStatus.OK);
    }

    @Operation(summary = "Deleta veículo por Id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VehicleDto> deleteVehicleById(@PathVariable Long id){
        vehicleService.deleteVehicleById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}