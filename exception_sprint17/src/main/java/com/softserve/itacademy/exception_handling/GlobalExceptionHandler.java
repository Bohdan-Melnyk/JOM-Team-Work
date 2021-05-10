package com.softserve.itacademy.exception_handling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView handleException(EntityNotFoundException entityNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("500");
        modelAndView.addObject("info", entityNotFoundException.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(NullEntityReferenceException nullEntityReferenceException) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("info", nullEntityReferenceException.getMessage());
        return modelAndView;
    }
}
