package com.souzatech.clickdesp.domain.dto.request;

import com.souzatech.clickdesp.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetRequest {

    private String token;
    private String newPassword;

}
