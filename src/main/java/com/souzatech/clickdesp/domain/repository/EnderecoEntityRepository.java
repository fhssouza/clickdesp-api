package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoEntityRepository extends JpaRepository<EnderecoEntity, Long>{
}
