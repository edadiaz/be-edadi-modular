package com.az.edadi.app;

import com.az.edadi.dal.config.DalConfig;
import com.az.edadi.post.PostConfig;
import com.az.edadi.user.UserConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        PostConfig.class,
        UserConfig.class,
        DalConfig.class
})
public class AppConfig {
}
