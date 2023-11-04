package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.ServicoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.ServicoResponse;
import com.souzatech.clickdesp.domain.model.Servico;

import java.util.List;

public interface ServicoService {

    List<ServicoResponse> findAll();

    Servico findById(Long id);

    ServicoResponse create(ServicoCreateRequest request);

    ServicoResponse update(Long id, ServicoCreateRequest request);

    void delete(Long id);


}
