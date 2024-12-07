package com.souzatech.clickdesp.infrastructure.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

}
