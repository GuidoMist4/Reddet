package net.bcsoft.com.Reddet.controller.exception;

import java.lang.RuntimeException;

public class ExceptionHandler extends RuntimeException{
    public ExceptionHandler(String message){super(message);}
}
