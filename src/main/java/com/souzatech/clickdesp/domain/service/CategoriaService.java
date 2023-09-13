package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CategoriaCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.CategoriaUpdateDTO;
import com.souzatech.clickdesp.domain.dto.response.CategoriaResponseDto;
import com.souzatech.clickdesp.domain.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<CategoriaResponseDto> findAll();

    CategoriaResponseDto findById(Long id);

    Categoria create(CategoriaCreateDTO dto);

    Categoria update(Long id, CategoriaUpdateDTO dto);

    void delete(Long id);



}
