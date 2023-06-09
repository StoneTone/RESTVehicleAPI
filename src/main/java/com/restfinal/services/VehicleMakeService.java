package com.restfinal.services;

import com.restfinal.domain.VehicleMake;

import java.util.List;
import java.util.Map;

public interface VehicleMakeService {

    List<VehicleMake> listAllMakes();

    VehicleMake getMakeById(Integer id);

    VehicleMake saveMake(VehicleMake vehicleMake);

    VehicleMake updateMake(VehicleMake vehicleMake);

    VehicleMake patchMake(Map<String, Object> updates, Integer makeId);

    Iterable<VehicleMake> saveMakeIterable(Iterable<VehicleMake> vehicleMakeIterable);

    void deleteMake(Integer id);

    VehicleMake findByMakeName(String makeName);
}
