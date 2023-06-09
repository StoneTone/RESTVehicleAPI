package com.restfinal.exceptions;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(Integer vehicleId){
        super("Vehicle with id of: " + vehicleId + " was not found :(");
    }
}
