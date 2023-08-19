package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class ItemOrdemServico {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorServico;

    private String observacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Servico servico;
}
