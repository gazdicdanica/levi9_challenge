package com.example.levi9_challenge.util;

import com.example.levi9_challenge.dto.ExceptionResponseDTO;
import com.example.levi9_challenge.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        return new ResponseEntity<>(new ExceptionResponseDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
