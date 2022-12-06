package net.bcsoft.com.Reddet.exception;

public class SpringReddetException extends RuntimeException {
    public SpringReddetException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringReddetException(String exMessage) {
        super(exMessage);
    }
}
