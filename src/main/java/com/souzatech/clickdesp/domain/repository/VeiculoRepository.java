package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.Servico;
import com.souzatech.clickdesp.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
