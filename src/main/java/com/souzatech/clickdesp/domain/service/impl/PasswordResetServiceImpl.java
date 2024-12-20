package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.dto.request.PasswordResetRequest;
import com.souzatech.clickdesp.domain.model.PasswordResetToken;
import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.repository.PasswordResetTokenRepository;
import com.souzatech.clickdesp.domain.repository.UsuarioRepository;
import com.souzatech.clickdesp.domain.service.PasswordResetService;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.infrastructure.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    public static final String MSG_EMAIL_NAO_ENCONTRADO = "Não existe usuario cadastrado com o E-mail: %s";
    public static final String MSG_TOKEN_NAO_ENCONTRADO = "Token não encontrado";
    public static final String MSG_TOKEN_EXPIRADO = "Token expirado";


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
        Usuario email = usuarioRepository.findByEmail(passwordResetRequest.getEmail());

        if (email == null){
            throw new NotFoundException(
                    String.format(MSG_EMAIL_NAO_ENCONTRADO,passwordResetRequest.getEmail()));
        }

        Optional<PasswordResetToken> resetTokenOpt = passwordResetTokenRepository.findByToken(passwordResetRequest.getToken());

        if (resetTokenOpt.isEmpty()) {
            throw new NotFoundException(
                    String.format(MSG_TOKEN_NAO_ENCONTRADO));
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (resetToken.getExpiryData().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException(
                    String.format(MSG_TOKEN_EXPIRADO));
        }

        Usuario usuario = resetToken.getUsuario();

        usuario.setSenha((pe.encode(passwordResetRequest.getNewPassword())));

        passwordResetTokenRepository.delete(resetToken);

        usuarioRepository.save(usuario);
    }

}
