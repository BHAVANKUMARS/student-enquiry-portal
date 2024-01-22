package com.ashokit.exception;

import lombok.Data;

@Data
public class DataValidationException extends RuntimeException{

    private int code;


    public DataValidationException(){

    }

    public DataValidationException(int code,String message){

        super(message);

        this.code = code;

    }

}
