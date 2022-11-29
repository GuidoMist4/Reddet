package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        return templateEngine;
    }

    public String Build(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine().process("MailTemplate", context);
    }
}
