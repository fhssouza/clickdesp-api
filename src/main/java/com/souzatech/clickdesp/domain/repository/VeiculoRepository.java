package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.dto.response.VeiculoParaLicenciamentoResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoPorMesResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoVencimentoResponse;
import com.souzatech.clickdesp.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlacaIgnoreCase(String placa);

    @Query("SELECT new com.souzatech.clickdesp.domain.dto.response.VeiculoPorMesResponse(YEAR(v.createAt), MONTH(v.createAt), COUNT(v)) " +
            "FROM Veiculo v " +
            "GROUP BY YEAR(v.createAt), MONTH(v.createAt) " +
            "ORDER BY YEAR(v.createAt), MONTH(v.createAt)")
    List<VeiculoPorMesResponse> countVeiculosPorMes();

    @Query("SELECT new com.souzatech.clickdesp.domain.dto.response.VeiculoVencimentoResponse(v.placa, v.modelo, l.dataVencimento) " +
           "FROM Veiculo v JOIN Licenciamento l ON FUNCTION('RIGHT', v.placa, 2) = l.finaPlaca " +
           "WHERE l.dataVencimento BETWEEN :dataInicio AND :dataFim")
    List<VeiculoVencimentoResponse> listarVeiculosParaLicenciamento(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT new com.souzatech.clickdesp.domain.dto.response.VeiculoParaLicenciamentoResponse(v.placa, v.modelo, v.marca, p.nome, l.dataVencimento) " +
            "FROM Veiculo v " +
            "JOIN v.proprietario p " +
            "JOIN Licenciamento l ON SUBSTRING(v.placa, LENGTH(v.placa) - 1, 2) = l.finaPlaca " +
            "WHERE l.finaPlaca = :finalPlaca AND p.id = :proprietarioId")
    List<VeiculoParaLicenciamentoResponse> findByFinalPlacaAndProprietario(
            @Param("finalPlaca") String finalPlaca,
            @Param("proprietarioId") Long proprietarioId);

}
