package com.tperuch.edstest.controller;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "veiculos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> saveVehicle(@RequestBody VehicleDto vehicleDto){
        return new ResponseEntity(vehicleService.saveVehicle(vehicleDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<VehicleDto>> getPaginatedVehicles(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage
            ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage);
        return new ResponseEntity(vehicleService.findAll(pageRequest), HttpStatus.OK);
    }
}