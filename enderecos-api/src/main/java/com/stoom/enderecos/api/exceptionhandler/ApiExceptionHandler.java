package com.stoom.enderecos.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSourse;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var errorFields = new ArrayList<ErrorResponse.Campo>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSourse.getMessage(error, LocaleContextHolder.getLocale());
			
			errorFields.add(new ErrorResponse.Campo(nome, mensagem));
		}
		 
		var errorResponse = new ErrorResponse();
		errorResponse.setStatus(status.value());
		errorResponse.setTitulo("Um ou mais campos inválidos. Faça o preenchimento correto e tente novamente");
		errorResponse.setDataHora(LocalDateTime.now());
		errorResponse.setCampos(errorFields);
		
		return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
	}
}
