package com.souzatech.clickdesp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public String instantiateDatabase(){
        return "Profile de produção iniciado - Banco PostgreSQL";
    }

}
