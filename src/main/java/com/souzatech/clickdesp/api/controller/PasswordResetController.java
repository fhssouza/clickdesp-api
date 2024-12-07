package com.souzatech.clickdesp.api.controller;

import com.souzatech.clickdesp.domain.dto.request.PasswordResetRequest;
import com.souzatech.clickdesp.domain.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        passwordResetService.updatePassword(request);
        return ResponseEntity.ok("Senha alterada com sucesso.");
    }
}
