package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "descricao",
        "preco",
        "categoria"
})
public class ServicoResponse {

   @JsonProperty("id")
   @Schema(description="ID", example = "1")
   private Long id;

   @JsonProperty("descricao")
   @Schema(description="Descrição", example = "LICENCIAMENTO")
   private String descricao;

   @JsonProperty("preco")
   @Schema(description="Preço", example = "2000.00")
   private Double preco;

   @JsonProperty("categoria")
   @Schema(description="Categoria", example = "2")
   private String categoriaDescricao;

}
