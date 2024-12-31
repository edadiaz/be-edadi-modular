package com.az.edadi.app.config;

import com.az.edadi.auth.config.AuthConfig;
import com.az.edadi.dal.config.DalConfig;
import com.az.edadi.post.config.PostConfig;
import com.az.edadi.university.config.UniversityConfig;
import com.az.edadi.user.config.UserConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        PostConfig.class,
        UserConfig.class,
        DalConfig.class,
        UniversityConfig.class,
        AuthConfig.class
})
public class AppConfig {
}
