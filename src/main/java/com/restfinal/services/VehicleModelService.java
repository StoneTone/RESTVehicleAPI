package com.restfinal.services;

import com.restfinal.domain.VehicleModel;
import com.restfinal.domain.dto.VehicleRequest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface VehicleModelService {

    Iterable<VehicleModel> listAllModels();

    VehicleModel getModelById(Integer id);

    VehicleModel saveModel(VehicleModel vehicleModel);

    VehicleModel updateModel(VehicleModel vehicleModel);

    VehicleModel patchModel(Map<String, Object> updates, Integer modelId);

    Iterable<VehicleModel> saveModelIterable(Iterable<VehicleModel> vehicleModelIterable);

    void deleteModel(Integer id);

    VehicleModel findByModelName(String modelName);
}
