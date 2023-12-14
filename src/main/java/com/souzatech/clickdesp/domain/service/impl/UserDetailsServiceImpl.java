package com.souzatech.clickdesp.domain.service.impl;

import com.souzatech.clickdesp.domain.model.Usuario;
import com.souzatech.clickdesp.domain.repository.UsuarioRepository;
import com.souzatech.clickdesp.infrastructure.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);
        if (usuario == null){
            throw  new UsernameNotFoundException(email);
        }
        return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
