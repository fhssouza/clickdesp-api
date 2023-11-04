package com.souzatech.clickdesp.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.souzatech.clickdesp.domain.model.Categoria;
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

   @JsonProperty("Id")
   private Long id;

   @JsonProperty("Descrição")
   private String descricao;

   @JsonProperty("Preço")
   private Double preco;

   @JsonProperty("Categoria")
   private Categoria categoria;

}
