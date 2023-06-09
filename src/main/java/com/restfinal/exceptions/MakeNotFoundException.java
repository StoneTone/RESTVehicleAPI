package com.restfinal.exceptions;

public class MakeNotFoundException extends RuntimeException{

    public MakeNotFoundException(Integer makeId)
    {
        super("Could not find make id of: " + makeId);
    }
}
