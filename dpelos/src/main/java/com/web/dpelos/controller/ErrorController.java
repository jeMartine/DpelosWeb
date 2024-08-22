package com.web.dpelos.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.web.dpelos.exception.NotFoundException;


@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(NotFoundException.class)
    public String error(NotFoundException e, Model model) {

        model.addAttribute("id", e.getId());

        return ("Error/paginaError");
    }
}
