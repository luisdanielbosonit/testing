package com.bosonit.training.testing.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError hadlerEntytyNotFoutException(EntityNotFoundException e) {
        return new CustomError(e.getMessage(), HttpStatus.FOUND);
    }

    @ExceptionHandler(value = {UnprocessableEntityException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomError handleUnprocessableEntityException(EntityNotFoundException e) {
        return new CustomError(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

    }
}




