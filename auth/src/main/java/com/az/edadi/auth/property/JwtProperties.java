package com.az.edadi.auth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String title;
    private String secretKey;
    private long accessTokenSessionTime;
    private long refreshTokenSessionTime;
}
