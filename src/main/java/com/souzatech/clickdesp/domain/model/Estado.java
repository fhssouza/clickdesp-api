package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.dto.request.EstadoCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estado {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    public Estado(EstadoCreateRequest request){
        this.nome = request.getNome();
    }

    public Estado(long estadoId) {
        id = estadoId;
    }
}
