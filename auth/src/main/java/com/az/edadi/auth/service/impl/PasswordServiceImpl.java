package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.model.request.ForgotPasswordRequest;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.PasswordService;
import com.az.edadi.common_service.service.SecurityMailSender;
import com.az.edadi.common_service.service.Translator;
import com.az.edadi.dal.entity.User;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    @Value("${app.domain}")
    private String domain;

    private final SecurityMailSender securityMailSender;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Override
    public void sendResetPasswordEmail(ForgotPasswordRequest forgotPasswordRequest) {
        var user = userRepository.findByUsernameOrEmail(
                forgotPasswordRequest.usernameOrEmail(), forgotPasswordRequest.usernameOrEmail())
                .orElseThrow();
        securityMailSender.sendResetPasswordLink(user.getEmail(), getMailInfoMap(user));
    }

    HashMap<String, String> getMailInfoMap(User user) {
        var info = new HashMap<String, String>();
        info.put("name", user.getName());
        info.put("greeting", Translator.getTranslation("greeting"));
        info.put("link", getRecoveryLink(user));
        info.put("we_send_password_reset_link", Translator.getTranslation("we-send-password-reset-link"));
        info.put("reset_password", Translator.getTranslation("reset-password"));
        return info;
    }

    String getRecoveryLink(User user) {
        String token = jwtService.generateAccessToken(user.getId(),user.getUsername(),null);
        return StringUtils.join(domain, "/auth/reset-password?token=",token);
    }
}
