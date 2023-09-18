package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
}
