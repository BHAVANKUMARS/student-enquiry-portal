package com.ashokit.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleGlobalException {

    private static final Logger logger = LoggerFactory.getLogger(HandleGlobalException.class);

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity handleDataValidationException(DataValidationException validationException){

        logger.info("exception message "+validationException.toString());

        return new ResponseEntity<>(validationException.getMessage(), HttpStatus.valueOf(validationException.getCode()));

    }


}
