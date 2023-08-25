package com.souzatech.clickdesp.domain.model.enums;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;
}
