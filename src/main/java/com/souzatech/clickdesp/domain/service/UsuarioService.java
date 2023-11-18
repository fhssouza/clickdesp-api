package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.UsuarioCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponse> findAll();

    UsuarioResponse findById(Long id);

    UsuarioResponse create(UsuarioCreateRequest request);

    UsuarioResponse update(Long id, UsuarioCreateRequest request);

    void delete(Long id);

}
