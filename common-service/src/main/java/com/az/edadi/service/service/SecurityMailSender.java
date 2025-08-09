package com.az.edadi.service.service;

import freemarker.template.Configuration;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SecurityMailSender {

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Async
    public void sendResetPasswordLink(String to, Map<String, String> mailMessage) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("reset-password-mail.html"), mailMessage);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(Translator.getTranslation("password-reset-mail"));
            mimeMessageHelper.setFrom(new InternetAddress("security@edadi.az", "Edadi.az"));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
