package com.souzatech.clickdesp.domain.model;

import com.souzatech.clickdesp.domain.dto.request.EstadoRequest;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Estado {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nome;

    public Estado(EstadoRequest request){
        this.nome = request.getNome();
    }
}
