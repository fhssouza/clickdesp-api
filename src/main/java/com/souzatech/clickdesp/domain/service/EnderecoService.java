package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.EnderecoCreateResquest;
import com.souzatech.clickdesp.domain.dto.response.EnderecoResponse;
import com.souzatech.clickdesp.domain.model.Endereco;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponse> findAll();

    Endereco findById(Long id);

    EnderecoResponse create(EnderecoCreateResquest request);

    EnderecoResponse update(Long id, EnderecoCreateResquest request);

    void delete(Long id);


}
