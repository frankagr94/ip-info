package com.mercadolibre.ipinfo.integration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CurrencyExchangeRates {

    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Object rates;

}
