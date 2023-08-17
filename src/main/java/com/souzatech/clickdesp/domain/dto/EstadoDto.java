package com.souzatech.clickdesp.domain.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class EstadoDto {

    private Long id;

    private String nome;
}
