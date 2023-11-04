package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.EstadoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.EstadoResponse;
import com.souzatech.clickdesp.domain.model.Estado;

import java.util.List;

public interface EstadoService {

    List<EstadoResponse> findAll();

    Estado findById(Long id);

    EstadoResponse create(EstadoCreateRequest request);

    EstadoResponse update(Long id, EstadoCreateRequest request);

    void delete(Long id);

}
