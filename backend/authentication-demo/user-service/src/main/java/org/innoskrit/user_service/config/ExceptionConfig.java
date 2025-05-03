package org.innoskrit.user_service.config;

import org.innoskrit.auth_lib.exception.CustomExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {
    @Bean
    public CustomExceptionHandler customExceptionHandler() {
        return new CustomExceptionHandler();
    }
}
