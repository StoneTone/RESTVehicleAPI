package com.restfinal.services;

import com.restfinal.domain.Vehicle;

import java.util.List;
import java.util.Map;

public interface VehicleService {

    Iterable<Vehicle> listAllVehicles();

    Vehicle getVehicleById(Integer vehicleId);

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    Vehicle patchVehicle(Map<String, Object>updates , Integer vehicleId);

    Iterable<Vehicle> saveVehicleIterable(Iterable<Vehicle> vehicleIterable);

    void deleteVehicle(Integer vehicleId);
}
