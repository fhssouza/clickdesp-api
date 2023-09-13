package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.ServicoCreateDTO;
import com.souzatech.clickdesp.domain.dto.request.ServicoUpdateDTO;
import com.souzatech.clickdesp.domain.dto.response.ServicoResponseDTO;
import com.souzatech.clickdesp.domain.model.Servico;

import java.util.List;

public interface ServicoService {

    List<ServicoResponseDTO> findAll();

    Servico findById(Long id);

    Servico create(ServicoCreateDTO dto);

    Servico update(Long id, ServicoUpdateDTO dto);

    void delete(Long id);


}
