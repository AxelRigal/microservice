package com.microservice.commandesservice.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommandeIntrouvableException extends RuntimeException {
    public CommandeIntrouvableException(String s) {
        super(s);
    }
}