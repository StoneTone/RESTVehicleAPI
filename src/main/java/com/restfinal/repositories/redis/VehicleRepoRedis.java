package com.restfinal.repositories.redis;

import com.restfinal.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepoRedis extends CrudRepository<Vehicle, Integer> {

    List<Vehicle> findAll();
}
