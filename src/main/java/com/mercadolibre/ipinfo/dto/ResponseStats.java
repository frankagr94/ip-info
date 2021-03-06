package com.mercadolibre.ipinfo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ResponseStats {

    private Object farthest_distance_country;
    private Object nearest_distance_country;
    private String average_distance;

}
