package com.souzatech.clickdesp.domain.model;


import com.souzatech.clickdesp.domain.dto.request.CategoriaRequestDto;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Categoria {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String descricao;

    public Categoria(CategoriaRequestDto dto) {
        this.descricao = dto.getDescricao();
    }

}
