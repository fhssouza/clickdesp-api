package com.souzatech.clickdesp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemOrdemServicoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ordemservico_id")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

}
