package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CidadeCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.CidadeUpdateDTO;
import com.souzatech.clickdesp.domain.dto.response.CidadeDTO;
import com.souzatech.clickdesp.domain.model.Cidade;

import java.util.List;

public interface CidadeService {

    List<CidadeDTO> findAll();

    CidadeDTO findById(Long id);

    Cidade create(CidadeCreateDTO dto);

    Cidade update(Long id, CidadeUpdateDTO dto);

    void delete(Long id);

}
