package com.springbrasil.repository.controller.handler;

import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
	@ResponseBody
    public ErrorMessage handleNotFound(NotFoundException e) {
		return new ErrorMessage("not found", 
				String.format("cannot found resource: '%s'", e.getMessage()));
    }
	
	class ErrorMessage {
		String error;
		String message;
		
		ErrorMessage(String error, String message) {
			this.error = error;
			this.message = message;
		}
		
	}
	
}
