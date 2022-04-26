package com.department.hotel.reservation.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.department.hotel.reservation.gateways.http.json.ErrorResponseJson;
import java.util.HashMap;
import lombok.val;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ErrorResponseJson handleNotFound(NotFoundException exception) {

    return new ErrorResponseJson(NOT_FOUND.value(), NOT_FOUND.name(), exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponseJson handleValidationError(MethodArgumentNotValidException exception) {

    val errorMessage = new HashMap<String, String>();
    exception.getBindingResult().getFieldErrors()
        .forEach(e -> errorMessage.put(e.getField(), e.getDefaultMessage()));

    return new ErrorResponseJson(BAD_REQUEST.value(), BAD_REQUEST.name(), errorMessage.toString());
  }
}
