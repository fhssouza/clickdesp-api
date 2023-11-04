package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CategoriaCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> findAll();

    CategoriaResponse findById(Long id);

    CategoriaResponse create(CategoriaCreateRequest request);

    CategoriaResponse update(Long id, CategoriaCreateRequest request);

    void delete(Long id);



}
