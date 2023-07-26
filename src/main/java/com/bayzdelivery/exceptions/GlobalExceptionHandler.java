package com.bayzdelivery.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = ApiRequestException.class)
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
    LOG.error(e.getMessage(), e);
    ApiException apiException = new ApiException(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST,
            e.getMessage()
    );
    return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
    LOG.error(e.getMessage(), e);
    ApiException apiException = new ApiException(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND,
            e.getMessage()
    );
    return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex) {
    ApiException apiException = new ApiException(
            ex.getStatusCode().value(),
            ex.getStatusCode(),
            ex.getMessage()
    );
    return new ResponseEntity<>(apiException,ex.getStatusCode());
  }

}
