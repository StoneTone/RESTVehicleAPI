package com.restfinal.exceptions;

public class VehicleExistsException extends RuntimeException{

   public VehicleExistsException(){
        super("Vehicle already exists");
    }
}
