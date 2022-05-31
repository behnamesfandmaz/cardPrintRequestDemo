package com.noway.cardPrintRequestDemo.framework.exception;

import com.noway.cardPrintRequestDemo.cardPrintRequest.controller.CardPrintRequestRestController;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHelper {

    private static final Logger LOG = Logger.getLogger(CardPrintRequestRestController.class.getName());

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        LOG.error("Invalid Input Exception: ",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = { HttpClientErrorException.Unauthorized.class })
    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
        LOG.error("Unauthorized Exception: ",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { BusinessException.class })
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        LOG.error("Business Exception: ",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { JspBusinessException.class })
    public ModelAndView handleJspBusinessException(JspBusinessException ex, HttpServletRequest request) {
        LOG.error("Jsp Business Exception: ",ex);
        ModelAndView mv=new ModelAndView();

        mv.addObject("exception",ex.getLocalizedMessage());
        mv.addObject("url",request.getRequestURL());
        mv.setViewName("error");

        return mv;
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        LOG.error("Exception: ",ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
