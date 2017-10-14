package com.gent.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by daria on 14.10.2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such good")
public class NotFoundException extends Exception {
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
