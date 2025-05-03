package org.innoskrit.user_service.config;

import org.innoskrit.auth_lib.JwtTokenUtil;
import org.innoskrit.auth_lib.SecurityConfigSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig extends SecurityConfigSupport {

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public String[] permittedPaths() {
        return new String[]{"/auth/login", "/auth/sign-up"};
    }
}

