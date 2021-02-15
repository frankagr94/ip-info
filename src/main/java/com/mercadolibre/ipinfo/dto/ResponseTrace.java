package com.mercadolibre.ipinfo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ApiModel
public class ResponseTrace {

    private String ip;
    private String date;
    private String country;
    private String iso_code;
    private List<String> languages;
    private List<String> currency;
    private List<String> times;
    private String estimated_distance;

}
