package com.souzatech.clickdesp.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public String instantiateDatabase(){
        return "Profile de desenvolvimento iniciado - Banco MySQL";
    }

}
