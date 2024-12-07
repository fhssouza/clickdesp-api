package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.PasswordResetRequest;
import com.souzatech.clickdesp.domain.model.PasswordResetToken;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.repository.PasswordResetTokenRepository;
import com.souzatech.clickdesp.domain.repository.UsuarioRepository;
import com.souzatech.clickdesp.domain.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenRepository repository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetServiceImpl(PasswordResetTokenRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder pe;


    @Override
    public void updatePassword(PasswordResetRequest passwordResetRequest) {
        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenRepository.findByToken(passwordResetRequest.getToken());

        if (resetTokenOpt.isEmpty()) {
            throw new IllegalArgumentException("Token inválido ou não encontrado.");
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (resetToken.getExpiryData().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado.");
        }

        Usuario usuario = resetToken.getUsuario();

        usuario.setSenha((pe.encode(passwordResetRequest.getNewPassword())));

        passwordResetTokenRepository.delete(resetToken);

        usuarioRepository.save(usuario);
    }

}
