package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class Servico {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "id.servico")
    private List<ItemOrdemServico> itens = new ArrayList<>();

    @JsonIgnore
    public List<OrdemServico> getOrdemServicos(){
        List<OrdemServico> lista = new ArrayList<>();
        for(ItemOrdemServico x : itens){
            lista.add(x.getOrdemServico());
        }
        return lista;
    }

}
