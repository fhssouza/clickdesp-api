package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servico implements Serializable {

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

    public Servico(Long servicoId) {
        id = servicoId;
    }

}
