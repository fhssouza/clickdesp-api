package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.VeiculoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponse;
import com.souzatech.clickdesp.domain.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    List<VeiculoResponse> findAll();

    Veiculo findById(Long id);

    Veiculo findByPlacaIgnoreCase(String placa);

    VeiculoResponse create(VeiculoCreateRequest request);

    VeiculoResponse update(Long id, VeiculoCreateRequest request);

    void delete(Long id);

}
