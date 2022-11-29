package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bcsoft.com.Reddet.controller.exception.FailedMailException;
import net.bcsoft.com.Reddet.model.NotificationEmail;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j // Log for J
public class MailService {
    private final JavaMailSender mailSender;

    private final MailContentBuilder mailContentBuilder;

    void SendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("rocco.bocchini@gmail.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipiente());
            mimeMessageHelper.setSubject(notificationEmail.getSoggetto());
            mimeMessageHelper.setText(notificationEmail.getCorpo());
        };
        try { mailSender.send(mimeMessagePreparator);
            log.info("The email has been sent.");
        } catch(MailException mailException){
            log.error("ERROR: The email has not been sent correctly.", mailException);
            throw new FailedMailException ("ERROR: The email has not been sent correctly." + notificationEmail.getRecipiente(), mailException);
        }
    }

}
