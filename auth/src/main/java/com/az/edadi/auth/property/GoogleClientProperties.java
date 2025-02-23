package com.az.edadi.auth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "auth.google")
public class GoogleClientProperties {
    private String clientId;
}
