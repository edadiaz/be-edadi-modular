package com.az.edadi.user;

import com.az.edadi.dal.no_sql.repository.RefreshTokenRepository;
import com.az.edadi.dal.no_sql.table.RefreshToken;
import com.az.edadi.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Main {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @GetMapping("api/v1/test")
    public String test() {
        long start = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        RefreshToken token = new RefreshToken();
        token.setTokenId(uuid);
        token.setUserId("ulvusuleymanovv");
        token.setStartDate(LocalDate.now());
        refreshTokenRepository.saveToken(token);
        return refreshTokenRepository.findByTokenId(uuid).toString() + (System.currentTimeMillis()-start) + " ms";
    }
}