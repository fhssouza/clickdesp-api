package com.souzatech.clickdesp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Long id;

    @Schema(description="Descrição da Categoria", example = "Habilitação")
    private String descricao;

}
