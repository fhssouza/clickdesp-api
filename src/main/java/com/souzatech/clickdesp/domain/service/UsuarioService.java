package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.UsuarioDTO;
import com.souzatech.clickdesp.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario create(UsuarioDTO dto);

    Usuario update(Long id, UsuarioDTO dto);

    void delete(Long id);

}
