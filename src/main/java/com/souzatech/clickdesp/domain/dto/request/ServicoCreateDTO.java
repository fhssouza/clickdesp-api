package com.souzatech.clickdesp.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServicoCreateDTO {

    @NotBlank(message = "campo nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo nome deve ter tamanho entre 5 e 30 caracteres" )
    private String descricao;

    private Double preco;

    private Long categoria;


}
