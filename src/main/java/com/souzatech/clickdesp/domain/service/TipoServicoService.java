package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.TipoServicoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.TipoServicoResponse;
import com.souzatech.clickdesp.domain.model.TipoServico;

import java.util.List;

public interface TipoServicoService {
    List<TipoServicoResponse> findAll();

    TipoServico findById(Long id);

    TipoServicoResponse create(TipoServicoCreateRequest request);

    TipoServicoResponse update(Long id, TipoServicoCreateRequest request);

    void delete(Long id);



}
