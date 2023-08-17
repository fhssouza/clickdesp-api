package com.souzatech.clickdesp.domain.dto;

import com.souzatech.clickdesp.domain.model.Estado;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class CidadeDto {

    private Long id;
    private String nome;
    private Estado estado;
}
