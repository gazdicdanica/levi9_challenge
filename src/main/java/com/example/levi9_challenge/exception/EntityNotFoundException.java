package com.example.levi9_challenge.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    private String message;

    public EntityNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
