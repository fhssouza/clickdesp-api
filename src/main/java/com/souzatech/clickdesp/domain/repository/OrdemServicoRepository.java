package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.dto.response.OrdemServicoStatusTotalResponse;
import com.souzatech.clickdesp.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    @Query("SELECT NEW com.souzatech.clickdesp.domain.dto.response.OrdemServicoStatusTotalResponse(o.status, COUNT(o)) " +
            "FROM OrdemServico o GROUP BY o.status")
    List<OrdemServicoStatusTotalResponse> countOrdensByStatus();

}
