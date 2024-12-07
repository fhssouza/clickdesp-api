package com.souzatech.clickdesp.infrastructure.service;

import com.souzatech.clickdesp.domain.dto.request.UsuarioCreateRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private static final String TEMPLATE_NAME = "registration";
    private static final String LOGO_IMAGE = "templates/images/logo.png";
    private static final String PGN_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Seja bem vindo(a)!";

    private final Environment environment;

    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;

    public EmailService(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.environment = environment;
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }


    public void sendMailWithInline(UsuarioCreateRequest user) throws MessagingException, UnsupportedEncodingException {
        String confirmationUrl = "generated_confirmation_url";
        String mailFrom = environment.getProperty("spring.email.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper email;
        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(user.getEmail());
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.getEmail());
        ctx.setVariable("name", user.getNome());
        ctx.setVariable("logo", LOGO_IMAGE);
        ctx.setVariable("url", confirmationUrl);

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

        email.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource(LOGO_IMAGE);

        email.addInline("logo", clr, PGN_MIME);

        mailSender.send(mimeMessage);
    }


    public void sendPasswordResetEmail(String toEmail, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Token para alteração de senha");

            String htmlContent = loadHtmlTemplate("templates/password-reset.html");

            htmlContent = htmlContent.replace("{{reset-token}}", token);

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email", e);
        }
    }


    private String loadHtmlTemplate(String templatePath) {
        try {
            ClassPathResource resource = new ClassPathResource(templatePath);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load email template", e);
        }
    }

}
