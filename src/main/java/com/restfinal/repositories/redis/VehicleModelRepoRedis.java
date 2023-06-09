package com.restfinal.repositories.redis;

import com.restfinal.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleModelRepoRedis extends CrudRepository<VehicleModel, Integer> {

    List<VehicleModel> findAll();

    VehicleModel findByModelName(String modelName);
}
