package com.restfinal.exceptions;

public class MakeExistsException extends RuntimeException{

    public MakeExistsException(){
        super("Make already exists");
    }
}
