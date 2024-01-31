package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long>{
}
