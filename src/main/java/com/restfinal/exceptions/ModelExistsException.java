package com.restfinal.exceptions;

public class ModelExistsException extends RuntimeException{

    public ModelExistsException(){
        super("Model already exists");
    }
}
