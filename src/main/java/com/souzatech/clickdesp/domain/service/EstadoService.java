package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.EstadoRequest;
import com.souzatech.clickdesp.domain.model.Estado;

import java.util.List;

public interface EstadoService {

    List<Estado> findAll();

    Estado findById(Long id);

    Estado create(EstadoRequest request);

    Estado update(Long id, EstadoRequest request);

    void delete(Long id);

}
