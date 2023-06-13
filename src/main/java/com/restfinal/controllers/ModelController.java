package com.restfinal.controllers;

import com.restfinal.domain.Vehicle;
import com.restfinal.domain.VehicleModel;
import com.restfinal.services.VehicleModelService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/model")
public class ModelController {

    private VehicleModelService vehicleModelService;

    public ModelController(VehicleModelService vehicleModelService) {
        this.vehicleModelService = vehicleModelService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable> getAllModels(){
        return ResponseEntity.ok(vehicleModelService.listAllModels());
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<VehicleModel> getModelById(@PathVariable Integer modelId)
    {
        return ResponseEntity.ok(vehicleModelService.getModelById(modelId));
    }

    @PostMapping("/")
    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<VehicleModel> createModel(@RequestBody VehicleModel vehicleModel)
    {

        return new ResponseEntity<>(
                vehicleModelService.saveModel(vehicleModel),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{modelId}/vehicle")
    public ResponseEntity<?> createVehicleWithModel(@PathVariable Integer modelId, @RequestBody Vehicle vehicle)
    {
        VehicleModel model = vehicleModelService.getModelById(modelId);
        model.getVehicleList().add(vehicle);
        vehicleModelService.updateModel(model);
        for (int i = 0; i < model.getVehicleList().size(); i++) {
            Vehicle vehicle1 = model.getVehicleList().get(i);
            if (vehicle1.getVin().equals(vehicle.getVin())) vehicle = vehicle1;
        }
        return new ResponseEntity<>(
                vehicle,
                HttpStatus.CREATED
        );
    }

    @PutMapping("")
    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<VehicleModel> updateModel(@RequestBody VehicleModel vehicleModel)
    {
        return new ResponseEntity<>(
                vehicleModelService.updateModel(vehicleModel),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{modelId}")
    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> patchModel(@PathVariable Integer modelId,
                                       @RequestBody Map<String, Object> updates)
    {
        return new ResponseEntity<>(
                vehicleModelService.patchModel(updates, modelId),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/{modelId}")
    @CacheEvict(value = "vehicleMake", allEntries = true)
    public ResponseEntity<?> deleteModel(@PathVariable Integer modelId)
    {
        vehicleModelService.deleteModel(modelId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Model ID of "  + modelId + " has been deleted");
    }
}
