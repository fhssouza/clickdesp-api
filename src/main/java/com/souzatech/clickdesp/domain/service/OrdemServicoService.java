package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.OrdemServicoDto;
import com.souzatech.clickdesp.domain.dto.request.OrdemServicoRequestDTO;
import com.souzatech.clickdesp.domain.model.OrdemServico;

import java.util.List;

public interface OrdemServicoService {

    List<OrdemServico> findAll();

    OrdemServico findById(Long id);

    OrdemServico create(OrdemServicoRequestDTO ordemServico);

    OrdemServico update(Long id, OrdemServicoDto dto);

    void delete(Long id);

}
