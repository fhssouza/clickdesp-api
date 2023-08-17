package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.CategoriaDto;
import com.souzatech.clickdesp.domain.dto.EstadoDto;
import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.model.Estado;

import java.util.List;

public interface EstadoService {

    List<Estado> findAll();

    Estado findById(Long id);

    Estado create(EstadoDto dto);

    Estado update(Long id, EstadoDto dto);

    void delete(Long id);

}
