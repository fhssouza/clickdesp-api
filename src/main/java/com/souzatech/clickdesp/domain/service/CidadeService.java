package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.CidadeDto;
import com.souzatech.clickdesp.domain.model.Cidade;

import java.util.List;

public interface CidadeService {

    List<Cidade> findAll();

    Cidade findById(Long id);

    Cidade create(CidadeDto dto);

    Cidade update(Long id, CidadeDto dto);

    void delete(Long id);

}
