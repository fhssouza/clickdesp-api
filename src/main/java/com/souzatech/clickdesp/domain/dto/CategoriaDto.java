package com.souzatech.clickdesp.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CategoriaDto {

    private Long id;

    private String descricao;

}
