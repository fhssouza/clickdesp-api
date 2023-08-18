package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.VeiculoDto;
import com.souzatech.clickdesp.domain.model.Veiculo;

import java.util.List;

public interface VeiculoService {

    List<Veiculo> findAll();

    Veiculo findById(Long id);

    Veiculo create(VeiculoDto dto);

    Veiculo update(Long id, VeiculoDto dto);

    void delete(Long id);

}
