package com.restfinal.exceptions;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(Integer modelId){
        super("Could not find model id of " + modelId);
    }
}
