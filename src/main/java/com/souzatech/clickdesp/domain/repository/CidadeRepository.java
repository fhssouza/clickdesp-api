package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.Cidade;
import com.souzatech.clickdesp.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
}
