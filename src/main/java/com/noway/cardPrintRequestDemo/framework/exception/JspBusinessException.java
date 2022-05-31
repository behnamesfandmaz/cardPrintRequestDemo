package com.noway.cardPrintRequestDemo.framework.exception;

public class JspBusinessException extends RuntimeException{

    public JspBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public JspBusinessException(String message){
        super(message);
    }
}
