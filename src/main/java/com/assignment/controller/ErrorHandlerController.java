package com.assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.core.annotation.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import com.assignment.entity.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);
 
   @ExceptionHandler(Throwable.class)
   protected ResponseEntity<ApiError> unexpectedErrorFound(
          Throwable ex) {
	          
	   ApiError apiError = new ApiError(500L,"Api general error. Please wait and try again.");
       logger.error("UnexpectedError: " + ex.getMessage());
       return  new ResponseEntity<ApiError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
   }
}