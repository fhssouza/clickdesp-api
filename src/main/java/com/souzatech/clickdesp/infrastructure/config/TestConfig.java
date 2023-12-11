package com.souzatech.clickdesp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public String instantiateDatabase(){
        return "Profile de teste iniciado - Banco H2";
    }

}
