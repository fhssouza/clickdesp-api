package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.CidadeCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.CidadeResponse;

import java.util.List;

public interface CidadeService {

    List<CidadeResponse> findAll();

    CidadeResponse findById(Long id);

    CidadeResponse create(CidadeCreateRequest request);

    CidadeResponse update(Long id, CidadeCreateRequest request);

    void delete(Long id);

}
