package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.dto.response.LicenciamentoResponse;
import com.souzatech.clickdesp.domain.model.Licenciamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenciamentoRepository extends JpaRepository<Licenciamento, Long> {

    @Query("SELECT new com.souzatech.clickdesp.domain.dto.response.LicenciamentoResponse(l.id, l.finaPlaca, l.dataVencimento, l.anoReferencia) FROM Licenciamento l WHERE l.anoReferencia = :ano")
    List<LicenciamentoResponse> findByanoReferencia(@Param("ano") Integer ano);

}
