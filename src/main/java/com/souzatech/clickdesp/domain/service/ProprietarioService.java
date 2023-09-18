package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioResponseDTO;
import com.souzatech.clickdesp.domain.dto.request.ProprietarioRequestDTO;
import com.souzatech.clickdesp.domain.model.Proprietario;

import java.util.List;

public interface ProprietarioService {

    List<ProprietarioResponseDTO> findAll();

    Proprietario findById(Long id);

    Proprietario create(ProprietarioRequestDTO dto);

    Proprietario update(Long id, ProprietarioResponseDTO dto);

    void delete(Long id);

}
