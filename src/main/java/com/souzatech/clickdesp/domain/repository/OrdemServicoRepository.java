package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.OrdemServico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
