package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.model.Proprietario;

import java.util.List;

public interface ProprietarioService {

    List<Proprietario> findAll();

    Proprietario findById(Long id);

    Proprietario create(ProprietarioDto dto);

    Proprietario update(Long id, ProprietarioDto dto);

    void delete(Long id);

}
