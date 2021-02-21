package com.mercadolibre.ipinfo.integration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResponseCountryInfo {

    private String name;

    private String cioc;

    private List<Language> languages;

    private List<Currency> currencies;

    private List<String> timezones;

    private List<String> latlng;

}
