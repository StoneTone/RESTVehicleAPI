package com.restfinal.controllers.advice;

import com.restfinal.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String vehicleNotFound(VehicleNotFoundException vNF){
        return vNF.getLocalizedMessage();
    }

    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String modelNotFound(ModelNotFoundException mNF){
        return mNF.getLocalizedMessage();
    }

    @ExceptionHandler(MakeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String makeNotFound(MakeNotFoundException mNF){
        return mNF.getLocalizedMessage();
    }

    @ExceptionHandler(ModelExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String modelExists(ModelExistsException mOE){
        return mOE.getLocalizedMessage();
    }

    @ExceptionHandler(MakeExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String makeExists(MakeExistsException mEx){
        return mEx.getLocalizedMessage();
    }

    @ExceptionHandler(VehicleExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String vehicleExists(VehicleExistsException vEX){
        return vEX.getLocalizedMessage();
    }

     //General Exception Catch
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String generalExceptionHander(Exception ex){
        return "Server error exception: " + ex;
    }
}
