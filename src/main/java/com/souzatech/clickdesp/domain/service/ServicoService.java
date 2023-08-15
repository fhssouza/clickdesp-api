package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.ServicoDto;
import com.souzatech.clickdesp.domain.model.Servico;

import java.util.List;

public interface ServicoService {

    List<Servico> findAll();

    Servico findById(Long id);

    Servico create(ServicoDto dto);

    Servico update(Long id, ServicoDto dto);

    void delete(Long id);

}
