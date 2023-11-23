package com.souzatech.clickdesp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {

    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Long id;

    @Schema(description="Descrição da Categoria", example = "Habilitação")
    private String descricao;

    private String dataCriacao;

    public void setDataCriacao(String dataCriacao) {
        Calendar cal = Calendar.getInstance();
        this.dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm.ss EEEE").format(cal.getTime()).toUpperCase();
    }



}
