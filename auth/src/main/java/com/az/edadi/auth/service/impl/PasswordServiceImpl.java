package com.az.edadi.auth.service.impl;

import com.az.edadi.auth.constant.TokenType;
import com.az.edadi.auth.model.request.ForgotPasswordRequest;
import com.az.edadi.auth.model.request.ResetPasswordWithTokenRequest;
import com.az.edadi.auth.service.JwtService;
import com.az.edadi.auth.service.PasswordService;
import com.az.edadi.model.exception.UserNotFoundException;
import com.az.edadi.service.service.SecurityMailSender;
import com.az.edadi.service.service.Translator;
import com.az.edadi.dal.entity.user.User;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    @Value("${app.domain}")
    private String domain;

    private final SecurityMailSender securityMailSender;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendResetPasswordEmail(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByUsernameOrEmail(forgotPasswordRequest.usernameOrEmail(),
                forgotPasswordRequest.usernameOrEmail()).orElseThrow(() -> new UserNotFoundException("user-not-found-with-username-or-email"));

        securityMailSender.sendResetPasswordLink(user.getEmail(), getMailInfoMap(user));
    }

    @Override
    public void resetPasswordWithToken(ResetPasswordWithTokenRequest request) {
        var userId= jwtService.getUserId(TokenType.RESET_PASSWORD_TOKEN,request.token());
        var user = userRepository.findById(userId).orElseThrow();
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
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
        String token = jwtService.generateToken(TokenType.RESET_PASSWORD_TOKEN,user.getId(), List.of());
        return StringUtils.join(domain, "/auth/reset-password?token=",token);
    }
}
