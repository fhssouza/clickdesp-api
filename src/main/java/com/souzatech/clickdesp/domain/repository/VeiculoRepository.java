package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioPorMesResponse;
import com.souzatech.clickdesp.domain.dto.response.VeiculoPorMesResponse;
import com.souzatech.clickdesp.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
