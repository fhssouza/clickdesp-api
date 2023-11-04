package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.Estado;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCreateRequest {

    @NotBlank(message = "campo nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo nome deve ter tamanho entre 5 e 30 caracteres" )
    private String nome;

    public EstadoCreateRequest(Estado entity){
        this.nome = entity.getNome();
    }

}
