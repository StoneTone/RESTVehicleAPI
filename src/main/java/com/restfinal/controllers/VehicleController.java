package com.restfinal.controllers;

import com.restfinal.domain.Vehicle;
import com.restfinal.services.VehicleModelService;
import com.restfinal.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    final private VehicleService vehicleService;
    final private VehicleModelService vehicleModelService;

    @GetMapping("")
    public ResponseEntity<Iterable> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.listAllVehicles());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer vehicleId)
    {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }


    @PostMapping("/")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle)
    {
        return new ResponseEntity<>(
                vehicleService.saveVehicle(vehicle),
                HttpStatus.CREATED
            );
    }



    @PutMapping("")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle)
    {
        return new ResponseEntity<>(
                vehicleService.updateVehicle(vehicle),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{vehicleId}")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> patchVehicle(@PathVariable Integer vehicleId,
                                          @RequestBody Map<String, Object> updates)
    {
        return new ResponseEntity<>(
                vehicleService.patchVehicle(updates, vehicleId),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/{vehicleId}")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer vehicleId)
    {
        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Vehicle ID of "  + vehicleId + " has been deleted");
    }
}
