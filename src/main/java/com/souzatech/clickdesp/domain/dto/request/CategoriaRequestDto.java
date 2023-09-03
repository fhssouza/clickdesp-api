package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.Categoria;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CategoriaRequestDto {

    private String descricao;

    public CategoriaRequestDto(Categoria entity){
        this.descricao = entity.getDescricao();
    }

}
