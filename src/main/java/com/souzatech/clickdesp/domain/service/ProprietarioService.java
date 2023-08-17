package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.ProprietarioDto;
import com.souzatech.clickdesp.domain.dto.ServicoDto;
import com.souzatech.clickdesp.domain.model.Proprietario;
import com.souzatech.clickdesp.domain.model.Servico;

import java.util.List;

public interface ProprietarioService {

    List<Proprietario> findAll();

    Proprietario findById(Long id);

    Proprietario create(ProprietarioDto dto);

    Proprietario update(Long id, ProprietarioDto dto);

    void delete(Long id);

}
