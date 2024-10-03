package com.web.dpelos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("Error/paginaError");
        modelAndView.addObject("statusCode", HttpStatus.NOT_FOUND.value());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(Exception ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("Error/paginaError");
        modelAndView.addObject("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}