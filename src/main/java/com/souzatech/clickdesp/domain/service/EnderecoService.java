package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.EnderecoResquestDTO;
import com.souzatech.clickdesp.domain.dto.response.EnderecoResponseDTO;
import com.souzatech.clickdesp.domain.model.Endereco;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> findAll();

    Endereco findById(Long id);

    Endereco create(EnderecoResquestDTO dto);

    Endereco update(Long id, EnderecoResquestDTO dto);

    void delete(Long id);


}
