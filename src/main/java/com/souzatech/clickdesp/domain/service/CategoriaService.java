package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.CategoriaDto;
import com.souzatech.clickdesp.domain.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> findAll();

    Categoria findById(Long id);

    Categoria create(CategoriaDto dto);

    Categoria update(Long id, CategoriaDto dto);

    void delete(Long id);

}
