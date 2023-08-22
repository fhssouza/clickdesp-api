package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class OrdemServico {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    @ManyToOne
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private String observacao;

    @OneToMany(mappedBy = "id.ordemServico")
    private List<ItemOrdemServico> itens = new ArrayList<>();

    public double getValorTotal() {
        double soma = 0.0;
        for(ItemOrdemServico ios : itens){
            soma = soma + ios.getSubtotal();
        }
        return soma;
    }

}
