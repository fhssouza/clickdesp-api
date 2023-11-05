package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponse;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioCreateRequest;
import com.souzatech.clickdesp.domain.model.Proprietario;

import java.util.List;

public interface ProprietarioService {

    List<ProprietarioResponse> findAll();

    Proprietario findById(Long id);

    ProprietarioResponse create(ProprietarioCreateRequest request);

    ProprietarioResponse update(Long id, ProprietarioCreateRequest request);

    void delete(Long id);

}
