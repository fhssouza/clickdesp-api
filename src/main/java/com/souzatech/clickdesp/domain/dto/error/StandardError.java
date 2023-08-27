package com.souzatech.clickdesp.domain.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError implements Serializable {

    private Instant timestamp;
    private HttpStatus httpStatus;
    private Integer statusCode;
    private String error;
    private String message;
    private String path;

}
