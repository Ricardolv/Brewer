package com.richard.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.richard.brewer.service.exception.BusinessRuleException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<String> handleBusinessRuleException(BusinessRuleException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
