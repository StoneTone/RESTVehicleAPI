package com.restfinal.services.impl;

import com.restfinal.domain.Vehicle;
import com.restfinal.domain.VehicleMake;
import com.restfinal.exceptions.MakeExistsException;
import com.restfinal.exceptions.MakeNotFoundException;
import com.restfinal.exceptions.ModelNotFoundException;
import com.restfinal.exceptions.VehicleNotFoundException;
import com.restfinal.repositories.jpa.VehicleMakeRepo;
import com.restfinal.services.VehicleMakeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    private VehicleMakeRepo vehicleMakeRepo;

    public VehicleMakeServiceImpl(VehicleMakeRepo vehicleMakeRepo) {
        this.vehicleMakeRepo = vehicleMakeRepo;
    }

    @Override
    public List<VehicleMake> listAllMakes() {
        return vehicleMakeRepo.findAll();
    }

    @Override
    public VehicleMake getMakeById(Integer makeId) {
        return vehicleMakeRepo.findById(makeId)
                                .orElseThrow(() -> new VehicleNotFoundException(makeId));
    }

    @Override
    public VehicleMake updateMake(VehicleMake vehicleMake) {

        VehicleMake existingMake = vehicleMakeRepo.findByMakeName(vehicleMake.getMakeName());

        if(existingMake != null && existingMake.getMakeName().equals(vehicleMakeRepo.findByMakeName(vehicleMake.getMakeName()).getMakeName()))
        {
            throw new MakeExistsException();
        }
        else {
            return vehicleMakeRepo.save(vehicleMake);
        }
    }

    @Override
    public VehicleMake patchMake(Map<String, Object> updates, Integer makeId) {

        VehicleMake makeToPatch = vehicleMakeRepo.findById(makeId)
                .orElseThrow(() -> new MakeNotFoundException(makeId));

            updates.forEach((k,o) -> {
                try {
                    Field nameField = makeToPatch.getClass().getDeclaredField(k);
                    nameField.setAccessible(true);
                    nameField.set(makeToPatch, o);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new VehicleNotFoundException(makeId);
                }
            });

            VehicleMake findSimMake = vehicleMakeRepo.findByMakeName(makeToPatch.getMakeName());

        if (findSimMake != null && makeToPatch.getMakeName() != null && makeToPatch.getMakeName().equals(findSimMake.getMakeName())) {
            throw new MakeExistsException();
        }
        return vehicleMakeRepo.save(makeToPatch);
    }


    @Override
    public VehicleMake saveMake(VehicleMake vehicleMake) {

        VehicleMake existingMake = vehicleMakeRepo.findByMakeName(vehicleMake.getMakeName());
        if (existingMake != null && existingMake.getMakeName().equals(vehicleMake.getMakeName())) {
            throw new MakeExistsException();
        } else {
            return vehicleMakeRepo.save(vehicleMake);
        }
    }

    @Override
    public Iterable<VehicleMake> saveMakeIterable(Iterable<VehicleMake> vehicleMakeIterable) {
        return vehicleMakeRepo.saveAll(vehicleMakeIterable);
    }

    @Override
    public void deleteMake(Integer makeId) {
        if (!vehicleMakeRepo.existsById(makeId)) {
            throw new MakeNotFoundException(makeId);
        }
        vehicleMakeRepo.deleteById(makeId);
    }

    @Override
    public VehicleMake findByMakeName(String makeName) {
        return vehicleMakeRepo.findByMakeName(makeName);
    }

}
