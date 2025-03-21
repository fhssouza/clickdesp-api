package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.dto.response.ProprietarioPorMesResponse;
import com.souzatech.clickdesp.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    @Query("SELECT new com.souzatech.clickdesp.domain.dto.response.ProprietarioPorMesResponse(YEAR(p.createAt), MONTH(p.createAt), COUNT(p)) " +
            "FROM Proprietario p " +
            "GROUP BY YEAR(p.createAt), MONTH(p.createAt) " +
            "ORDER BY YEAR(p.createAt), MONTH(p.createAt)")
    List<ProprietarioPorMesResponse> countProprietariosPorMes();
}
