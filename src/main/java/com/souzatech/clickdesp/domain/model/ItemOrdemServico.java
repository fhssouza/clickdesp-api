package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souzatech.clickdesp.domain.dto.request.ItemOrdemServicoRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemOrdemServico  {

    @JsonIgnore
    @EmbeddedId
    private ItemOrdemServicoPK id = new ItemOrdemServicoPK();

    private Double desconto;

    private Integer quantidade;

    private Double preco;

    public ItemOrdemServico(){

    }
    public ItemOrdemServico(OrdemServico ordemServico, Servico servico, Double desconto, Integer quantidade, Double preco) {
        id.setOrdemServico(ordemServico);
        id.setServico(servico);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubtotal() {
        return (preco - desconto) * quantidade;
    }
    @JsonIgnore
    public OrdemServico getOrdemServico(){
        return id.getOrdemServico();
    }

    public void setOrdemServico(OrdemServico ordemServico){
        id.setOrdemServico(ordemServico);
    }

    public Servico getServico(){
        return id.getServico();
    }

    public void setServico(Servico servico){
        id.setServico(servico);
    }


}
