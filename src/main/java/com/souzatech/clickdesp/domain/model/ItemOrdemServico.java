package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
