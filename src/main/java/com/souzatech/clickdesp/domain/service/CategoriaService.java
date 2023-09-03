package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CategoriaRequestDto;
import com.souzatech.clickdesp.domain.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> findAll();

    Categoria findById(Long id);

    Categoria create(CategoriaRequestDto request);

    Categoria update(Long id, CategoriaRequestDto request);

    void delete(Long id);

}
