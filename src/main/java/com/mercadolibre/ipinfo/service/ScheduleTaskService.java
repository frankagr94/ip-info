package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.util.Util;
import com.mercadolibre.ipinfo.webclient.GeneralWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class ScheduleTaskService {

    @Autowired
    private GeneralWebClient generalWebClient;

    @Value("${currency.api_acces_key}")
    private String apiAccessKey;

    @Value("${currency.exchange}")
    private String currencyToExchange;

    public void currencyExchangeUpdate() throws Exception {
        if(currencyToExchange == null || currencyToExchange.trim().isEmpty()){
            throw new Exception("Property currency.exchange can not be null or empty, check the application properties");
        }

        var updatedRates = generalWebClient.getExchangeRates(apiAccessKey,currencyToExchange);

        if(!updatedRates.isSuccess()){
            return;
        }

        Util.CURRENCY_EXCHANGE_RATES = (LinkedHashMap) updatedRates.getRates();
    }

}
