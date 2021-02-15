package com.mercadolibre.ipinfo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class RequestTrace {

    private String ip;

}
