package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.VeiculoRequestDTO;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponseDTO;
import com.souzatech.clickdesp.domain.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    List<VeiculoResponseDTO> findAll();

    Veiculo findById(Long id);

    Veiculo create(VeiculoRequestDTO dto);

    Veiculo update(Long id, VeiculoRequestDTO dto);

    void delete(Long id);

}
