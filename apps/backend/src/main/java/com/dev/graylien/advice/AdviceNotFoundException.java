package com.dev.graylien.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdviceNotFoundException extends RuntimeException {
    public AdviceNotFoundException(){
        super("Tip not found");
    }
}
