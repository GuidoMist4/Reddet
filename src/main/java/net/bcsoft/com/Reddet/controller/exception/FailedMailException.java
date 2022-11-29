package net.bcsoft.com.Reddet.controller.exception;

import org.springframework.mail.MailException;

public class FailedMailException extends MailException {

    public FailedMailException(String message, Exception ex){super(message , ex);}

}
