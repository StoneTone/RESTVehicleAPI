package com.restfinal.repositories.redis;

import com.restfinal.domain.VehicleMake;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleMakeRepoRedis extends CrudRepository<VehicleMake, Integer> {

    List<VehicleMake> findAll();

    VehicleMake findByMakeName(String makeName);
}
