package com.souzatech.clickdesp.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Embeddable
public class ItemOrdemServicoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ordemservico_id")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

}
