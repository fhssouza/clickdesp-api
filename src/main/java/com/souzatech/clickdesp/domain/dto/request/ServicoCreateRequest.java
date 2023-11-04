package com.souzatech.clickdesp.domain.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoCreateRequest {

    @NotBlank(message = "campo nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo nome deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("Descrição")
    private String descricao;

    @JsonProperty("Preço")
    private Double preco;

    @JsonProperty("Categoria")
    private Long categoria;
}
