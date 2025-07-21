package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.LicenciamentoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.LicenciamentoResponse;

import java.util.List;

public interface LicenciamentoService {

    List<LicenciamentoResponse> findAll();

    List<LicenciamentoResponse> findByanoReferencia(Integer anoReferencia);

    LicenciamentoResponse create(LicenciamentoCreateRequest request);

    LicenciamentoResponse update(Long id, LicenciamentoCreateRequest request);

    void delete(Long id);

}
