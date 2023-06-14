package com.restfinal.controllers;

import com.restfinal.domain.VehicleMake;
import com.restfinal.services.VehicleMakeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/make")
public class MakeController {

    private VehicleMakeService vehicleMakeService;

    public MakeController(VehicleMakeService vehicleMakeService) {
        this.vehicleMakeService = vehicleMakeService;
    }

    //get all
    @GetMapping
//    @Cacheable(value = "vehicleMake")
    public ResponseEntity<List<VehicleMake>> getAllMake()
    {
        return ResponseEntity.ok(vehicleMakeService.listAllMakes());
    }
    //get by id
    @GetMapping("/{makeId}")
    public ResponseEntity<VehicleMake> getMakeById(@PathVariable Integer makeId)
    {
        return ResponseEntity.ok(vehicleMakeService.getMakeById(makeId));
    }
    //creating new make
    @PostMapping("/")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<VehicleMake> createMake(@RequestBody VehicleMake vehicleMake)
    {
        return new ResponseEntity<>(
                vehicleMakeService.saveMake(vehicleMake),
                HttpStatus.CREATED
        );
    }
    //updating make
    @PutMapping("")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<VehicleMake> updateVehicle(@RequestBody VehicleMake vehicleMake)
    {
        return new ResponseEntity<>(
                vehicleMakeService.updateMake(vehicleMake),
                HttpStatus.ACCEPTED
        );
    }
    //patching make
    @PatchMapping("/{makeId}")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> patchMake(@PathVariable Integer makeId,
                                          @RequestBody Map<String, Object> updates)
    {
        return new ResponseEntity<>(
                vehicleMakeService.patchMake(updates, makeId),
                HttpStatus.ACCEPTED
        );
    }
    //delete
    @DeleteMapping("/{makeId}")
//    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer makeId)
    {
        vehicleMakeService.deleteMake(makeId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Make ID of "  + makeId + " has been deleted");
    }
}
