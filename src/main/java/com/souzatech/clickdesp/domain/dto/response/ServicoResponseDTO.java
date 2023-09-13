package com.souzatech.clickdesp.domain.dto.response;

import com.souzatech.clickdesp.domain.model.Categoria;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class ServicoResponseDTO {

   private Long id;

   private String descricao;

   private Double preco;

   private Categoria categoria;

}
