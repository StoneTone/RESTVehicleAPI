package com.restfinal.services.impl;

import com.restfinal.domain.VehicleModel;
import com.restfinal.exceptions.*;
import com.restfinal.repositories.jpa.VehicleModelRepo;
import com.restfinal.services.VehicleMakeService;
import com.restfinal.services.VehicleModelService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    private VehicleModelRepo vehicleModelRepo;

    private VehicleMakeService vehicleMakeService;

    public VehicleModelServiceImpl(VehicleModelRepo vehicleModelRepo) {
        this.vehicleModelRepo = vehicleModelRepo;
    }

    @Override
    public List<VehicleModel> listAllModels() {
        return vehicleModelRepo.findAll();
    }

    @Override
    public VehicleModel getModelById(Integer id) {
        return vehicleModelRepo.findById(id)
                                .orElseThrow(() -> new ModelNotFoundException(id));
    }

    @Override
    public VehicleModel saveModel(VehicleModel vehicleModel) {
        VehicleModel existingModel = vehicleModelRepo.findByModelName(vehicleModel.getModelName());
        if (existingModel != null) {
            throw new ModelExistsException();
        } else {
            return vehicleModelRepo.save(vehicleModel);
        }

    }


    @Override
    public VehicleModel updateModel(VehicleModel vehicleModel) {
        VehicleModel existingModel = vehicleModelRepo.findByModelName(vehicleModel.getModelName());
        if(!existingModel.getModelId().equals(vehicleModel.getModelId()))
        {
            throw new ModelExistsException();
        }
        return vehicleModelRepo.save(vehicleModel);
    }

    @Override
    public VehicleModel patchModel(Map<String, Object> updates, Integer modelId) {
        VehicleModel modelToPatch = vehicleModelRepo.findById(modelId)
                .orElseThrow(() -> new MakeNotFoundException(modelId));

        if(modelToPatch.getModelName().equals(vehicleModelRepo.findByModelName(modelToPatch.getModelName()).getModelName()))
        {
            throw new ModelExistsException();
        }

        updates.forEach((k,o) -> {
            try {
                Field nameField = modelToPatch.getClass().getDeclaredField(k);
                nameField.setAccessible(true);
                nameField.set(modelToPatch, o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new VehicleNotFoundException(modelId);
            }
        });

        return vehicleModelRepo.save(modelToPatch);
    }

    @Override
    public Iterable<VehicleModel> saveModelIterable(Iterable<VehicleModel> vehicleModelIterable) {

        return vehicleModelRepo.saveAll(vehicleModelIterable);
    }

    @Override
    public void deleteModel(Integer id) {
        if (!vehicleModelRepo.existsById(id)) {
            throw new ModelNotFoundException(id);
        }
        vehicleModelRepo.deleteVehicleMakeAssociation(id);
        vehicleModelRepo.deleteById(id);
    }

    @Override
    public VehicleModel findByModelName(String modelName) {
        return vehicleModelRepo.findByModelName(modelName);
    }
}
