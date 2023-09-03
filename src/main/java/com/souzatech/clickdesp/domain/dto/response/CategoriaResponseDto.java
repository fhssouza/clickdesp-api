package com.souzatech.clickdesp.domain.dto.response;

import com.souzatech.clickdesp.domain.model.Categoria;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CategoriaResponseDto {

    private Long id;
    private String descricao;

    public static CategoriaResponseDto fromEntityDto(Categoria entity){
        return CategoriaResponseDto.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .build();
    }

}
