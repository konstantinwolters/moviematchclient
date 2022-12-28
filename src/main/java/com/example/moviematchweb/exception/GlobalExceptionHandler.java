package com.example.moviematchweb.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.moviematchweb.exception.customExceptions.NoResultsException;
import com.example.moviematchweb.exception.customExceptions.NoSuchRessourceException;

import feign.RetryableException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RetryableException.class)
    public String handleRetryable(Model model, RetryableException ex) {

        model.addAttribute("errorMessage", "Connection lost. Please try again later.");
        return "error";
    }

    @ExceptionHandler(NoSuchRessourceException.class)
    public String handleNoSuchRessource(Model model, NoSuchRessourceException ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NoResultsException.class)
    public String handleNoResults(Model model, NoResultsException ex) {

        model.addAttribute("IsNewSession", ex.getIsNewSession());
        model.addAttribute("errorMessage", ex.getMessage());

        return "noresults";
    }

}
