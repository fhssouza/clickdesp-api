package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.EnderecoEntityRequest;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioEnderecoResponse;
import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;

import java.util.List;

public interface ProprietarioService {

    List<ProprietarioResponse> findAll();

    ProprietarioResponse findById(Long id);

    ProprietarioResponse create(ProprietarioCreateRequest request);

    ProprietarioResponse update(Long id, ProprietarioCreateRequest request);

    void delete(Long id);

    ProprietarioEnderecoResponse createEndereco(Long proprietarioId, EnderecoEntityRequest endereco);

    ProprietarioEnderecoResponse findByIdProprietarioEndereco(Long id);

}
