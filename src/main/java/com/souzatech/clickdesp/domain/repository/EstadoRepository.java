package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.Categoria;
import com.souzatech.clickdesp.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
}
