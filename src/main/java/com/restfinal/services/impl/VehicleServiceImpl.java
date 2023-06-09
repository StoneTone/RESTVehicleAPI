package com.restfinal.services.impl;

import com.restfinal.domain.Vehicle;
import com.restfinal.exceptions.VehicleExistsException;
import com.restfinal.exceptions.VehicleNotFoundException;
import com.restfinal.repositories.jpa.VehicleModelRepo;
import com.restfinal.repositories.jpa.VehicleRepo;
import com.restfinal.services.VehicleService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo vehicleRepo;
    private VehicleModelRepo vehicleModelRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, VehicleModelRepo vehicleModelRepo) {
        this.vehicleRepo = vehicleRepo;
        this.vehicleModelRepo = vehicleModelRepo;
    }

    @Override
    public List<Vehicle> listAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        Vehicle existingLicense = vehicleRepo.findByLicensePlate(vehicle.getLicensePlate());
        Vehicle existingVin = vehicleRepo.findByVin(vehicle.getVin());
        if (existingLicense == null && existingVin == null) {
            return vehicleRepo.save(vehicle);
        } else if (existingLicense.getLicensePlate().equals(vehicle.getLicensePlate())) {
            throw new VehicleExistsException();
        } else if (existingVin.getVin().equals(vehicle.getVin())) {
            throw new VehicleExistsException();
        } else {
            return vehicleRepo.save(vehicle);
        }
    }

    @Override
    public Vehicle patchVehicle(Map<String, Object> updates, Integer vehicleId) {
        Vehicle vehicleToPatch = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));


        Vehicle existingLicense = vehicleRepo.findByLicensePlate(vehicleToPatch.getLicensePlate());
        Vehicle existingVin = vehicleRepo.findByVin(vehicleToPatch.getVin());
        if (!vehicleToPatch.getLicensePlate().equals(existingLicense.getLicensePlate())) {
            throw new VehicleExistsException();
        }
        else if (!vehicleToPatch.getVin().equals(existingVin.getVin())) {
            throw new VehicleExistsException();
        }
        else if(vehicleToPatch.getLicensePlate().isEmpty() || vehicleToPatch.getVin().isEmpty())
        {
            updates.forEach((k, o) -> {
                try {
                    Field nameField = vehicleToPatch.getClass().getDeclaredField(k);
                    nameField.setAccessible(true);
                    nameField.set(vehicleToPatch, o);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new VehicleNotFoundException(vehicleId);
                }
            });

            return vehicleRepo.save(vehicleToPatch);
        }
        else{
            updates.forEach((k, o) -> {
                try {
                    Field nameField = vehicleToPatch.getClass().getDeclaredField(k);
                    nameField.setAccessible(true);
                    nameField.set(vehicleToPatch, o);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new VehicleNotFoundException(vehicleId);
                }
            });

            return vehicleRepo.save(vehicleToPatch);
        }

    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate().equals(vehicleRepo.findByLicensePlate(vehicle.getLicensePlate()).getLicensePlate())) {
            throw new VehicleExistsException();
        }
        if (vehicle.getVin().equals(vehicleRepo.findByVin(vehicle.getVin()).getVin())) {
            throw new VehicleExistsException();
        }
        return vehicleRepo.save(vehicle);
    }

    @Override
    public Iterable<Vehicle> saveVehicleIterable(Iterable<Vehicle> vehicleIterable) {
        return vehicleRepo.saveAll(vehicleIterable);
    }

    @Override
    public void deleteVehicle(Integer vehicleId) {
        if (!vehicleRepo.existsById(vehicleId)) {
            throw new VehicleNotFoundException(vehicleId);
        }
        vehicleModelRepo.deleteVehicleAssociation(vehicleId);
        vehicleRepo.deleteById(vehicleId);
    }

}
