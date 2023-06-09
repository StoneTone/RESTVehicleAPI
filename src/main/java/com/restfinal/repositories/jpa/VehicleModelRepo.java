package com.restfinal.repositories.jpa;

import com.restfinal.domain.VehicleModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface VehicleModelRepo extends CrudRepository<VehicleModel, Integer> {

    List<VehicleModel> findAll();

    VehicleModel findByModelName(String modelName);

    @Modifying
    @Query(value = "DELETE FROM VEHICLE_MODEL_VEHICLE_LIST WHERE VEHICLE_LIST_VEHICLE_ID = :id", nativeQuery = true)
    @Transactional
    void deleteVehicleAssociation(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM VEHICLE_MAKE_MODEL_LIST WHERE MODEL_LIST_MODEL_ID = :id", nativeQuery = true)
    @Transactional
    void deleteVehicleMakeAssociation(@Param("id") int id);
}
