package com.souzatech.clickdesp.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.souzatech.clickdesp.domain.model.enums.StatusOrdemServico;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
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
