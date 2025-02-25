package com.az.edadi.user;

import com.az.edadi.common_service.service.SecurityMailSender;
import com.az.edadi.dal.no_sql.repository.RefreshTokenRepository;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Main {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SecurityMailSender mailSender;
    @GetMapping("api/v1/test")
    public String test() {
        Map<String,String> messages = new HashMap<>();
        messages.put("name","*");
        messages.put("link","");
        mailSender.sendResetPasswordLink("",messages);
        return "ok";
     }
}