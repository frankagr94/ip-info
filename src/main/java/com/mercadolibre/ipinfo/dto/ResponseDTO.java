package com.mercadolibre.ipinfo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ApiModel
public class ResponseDTO {

    private boolean result;
    private String message;
    private Object data;

}
