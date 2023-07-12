package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrdemServico {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private String observacao;

    @ManyToOne
    private Veiculo veiculo;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "proprietario_id", nullable = false)
//    private Proprietario proprietario;

    @OneToMany(mappedBy = "ordemServico")
    private List<ItemOrdemServico> itens = new ArrayList<>();
}
