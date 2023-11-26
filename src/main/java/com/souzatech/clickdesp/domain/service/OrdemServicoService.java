package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CreateOrdemServicoRequest;
import com.souzatech.clickdesp.domain.dto.response.OrdemServicoResponse;

import java.util.List;

public interface OrdemServicoService {

    List<OrdemServicoResponse> findAll();

    OrdemServicoResponse findById(Long id);

    OrdemServicoResponse create(CreateOrdemServicoRequest request);

    OrdemServicoResponse update(Long id, CreateOrdemServicoRequest request);

    OrdemServicoResponse finish(Long id);

    OrdemServicoResponse cancel(Long id);

}
