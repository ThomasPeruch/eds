package com.tperuch.edstest.controller;

import com.tperuch.edstest.dto.VehicleDto;
import com.tperuch.edstest.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "veiculos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDto> saveVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicleDto));
    }
}