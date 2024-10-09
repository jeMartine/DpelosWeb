package com.web.dpelos.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.dpelos.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*Este es el controlador de los errores, la verdad solo retorna la pagina de erro. asumo que en el front-end se debera mostrar cuando se
 * capturen errores.
 */
@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        ModelAndView modelAndView = new ModelAndView("Error/paginaError");
        modelAndView.addObject("statusCode", statusCode);
        modelAndView.addObject("errorMessage", exception == null ? "N/A" : exception.getMessage());

        return modelAndView;
    }

}
