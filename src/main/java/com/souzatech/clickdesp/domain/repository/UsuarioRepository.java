package com.souzatech.clickdesp.domain.repository;

import com.souzatech.clickdesp.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Transactional
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
