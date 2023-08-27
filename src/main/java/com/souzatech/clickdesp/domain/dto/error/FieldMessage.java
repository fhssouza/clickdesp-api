package com.souzatech.clickdesp.domain.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldMessage {

    private String fieldName;
    private String message;

    
}
