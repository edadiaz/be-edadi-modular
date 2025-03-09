package com.az.edadi.auth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth.facebook")
public class FacebookClientProperties {
    private String clientId;
}
