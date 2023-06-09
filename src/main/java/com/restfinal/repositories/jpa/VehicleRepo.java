package com.restfinal.repositories.jpa;

import com.restfinal.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepo extends CrudRepository<Vehicle, Integer> {

    List<Vehicle> findAll();

    Vehicle findByLicensePlate(String licensePlate);

    Vehicle findByVin(String vin);
}
