package com.az.edadi.common_service.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SecurityMailSender {


    private final JavaMailSender mailSender;
    private final Configuration configuration;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("security@edadi.az");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendResetPasswordLink(String to, Map<String,String> mailMessage) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Template template = configuration.getTemplate("reset-password-mail-az.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailMessage);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Şifrə bərpası");
            mimeMessageHelper.setFrom(new InternetAddress("security@edadi.az", "Edadi"));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
