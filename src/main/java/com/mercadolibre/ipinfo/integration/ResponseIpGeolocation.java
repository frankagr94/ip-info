package com.mercadolibre.ipinfo.integration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseIpGeolocation {

    private String countryCode;

    private String countryCode3;

    private String countryName;

    private String countryEmoji;

}
