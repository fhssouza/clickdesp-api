package com.souzatech.clickdesp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

    private String email;
    private String senha;
}
