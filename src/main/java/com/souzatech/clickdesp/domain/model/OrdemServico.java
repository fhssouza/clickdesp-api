package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tipoServico_id", nullable = false)
    private TipoServico tipoServico;

    private String observacao;

    private Instant createAt;

    private Instant updateAt;

    @OneToMany(mappedBy = "id.ordemServico")
    private List<ItemOrdemServico> itens = new ArrayList<>();

    public double getValorTotal() {
        double soma = 0.0;
        for(ItemOrdemServico ios : itens){
            soma = soma + ios.getSubtotal();
        }
        return soma;
    }

    @PrePersist
    public void prePersist(){
        createAt = Instant.now();
        status = StatusOrdemServico.ABERTA;
    }

    @PreUpdate
    public void preUpdate(){
        updateAt = Instant.now();
    }

}
