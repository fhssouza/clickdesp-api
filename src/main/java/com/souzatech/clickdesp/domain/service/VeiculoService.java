package com.souzatech.clickdesp.domain.service;

import com.souzatech.clickdesp.domain.dto.request.VeiculoCreateRequest;
import com.souzatech.clickdesp.domain.dto.response.VeiculoParaLicenciamentoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoPorMesResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoVencimentoResponse;
import com.souzatech.clickdesp.domain.model.Veiculo;

import java.time.LocalDate;
import java.util.List;

public interface VeiculoService {

    List<VeiculoResponse> findAll();

    Veiculo findById(Long id);

    Veiculo findByPlacaIgnoreCase(String placa);

    VeiculoResponse create(VeiculoCreateRequest request);

    VeiculoResponse update(Long id, VeiculoCreateRequest request);

    void delete(Long id);

    Long countVeiculo();

    List<VeiculoPorMesResponse> countVeiculosPorMes();

    List<VeiculoVencimentoResponse> listarVeiculosParaLicenciamento(LocalDate dataInicio, LocalDate dataFim);

    List<VeiculoParaLicenciamentoResponse> findByFinalPlacaAndProprietario(String finalPlaca, Long proprietarioId);

}
