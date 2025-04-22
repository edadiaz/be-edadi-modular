package com.az.edadi.app.config;

import com.az.edadi.auth.config.AuthConfig;
import com.az.edadi.common.config.CommonConfig;
import com.az.edadi.dal.config.DalConfig;
import com.az.edadi.file_storage.config.FileStorageConfig;
import com.az.edadi.message.config.MessageConfig;
import com.az.edadi.model.config.ModelConfig;
import com.az.edadi.post.config.PostConfig;
import com.az.edadi.roommate.config.RoommateConfig;
import com.az.edadi.service.config.ServiceConfig;
import com.az.edadi.speciality.config.SpecialityConfig;
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
        AuthConfig.class,
        ServiceConfig.class,
        ModelConfig.class,
        FileStorageConfig.class,
        SpecialityConfig.class,
        MessageConfig.class,
        CommonConfig.class,
        RoommateConfig.class
})
public class AppConfig {
}
