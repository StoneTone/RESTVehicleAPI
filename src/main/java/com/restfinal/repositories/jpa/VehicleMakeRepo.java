package com.restfinal.repositories.jpa;

import com.restfinal.domain.VehicleMake;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleMakeRepo extends CrudRepository<VehicleMake, Integer> {

    List<VehicleMake> findAll();

    VehicleMake findByMakeName(String makeName);
}
