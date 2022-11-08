package com.bosonit.training.testing.Exception;

import org.springframework.http.HttpStatus;
import java.util.Date;

public class UnprocessableEntityException extends RuntimeException {

    Date timeStamp;
    private HttpStatus httpStatus;

    public UnprocessableEntityException(String message, HttpStatus httpStatus, Date timeStamp) {
        super(message);
        this.httpStatus=httpStatus;
        this.timeStamp=timeStamp;


    }
}
