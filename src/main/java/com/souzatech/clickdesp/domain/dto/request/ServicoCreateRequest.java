package com.souzatech.clickdesp.domain.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Size(min = 5, max = 30, message = "Campo Descrição deve ter tamanho entre 5 e 30 caracteres" )
    @JsonProperty("descricao")
    @Schema(description="Descrição", example = "LICENCIAMENTO")
    private String descricao;

    @JsonProperty("preco")
    @Schema(description="Preço", example = "2000.00")
    private Double preco;

    @JsonProperty("categoria")
    @Schema(description="Categoria", example = "2")
    private Long categoria;
}
