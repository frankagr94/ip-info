package com.mercadolibre.ipinfo.webclient;

import com.mercadolibre.ipinfo.integration.CurrencyExchangeRates;
import com.mercadolibre.ipinfo.integration.ResponseCountryInfo;
import com.mercadolibre.ipinfo.integration.ResponseIpGeolocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GeneralWebClient {

    private static final Logger log = LoggerFactory.getLogger(GeneralWebClient.class);

    public ResponseIpGeolocation getIpGeolocationInfo(String ip){
        log.info("IP INFO - GeneralWebClient - getIpGeolocationInfo");

        Mono<ResponseIpGeolocation> mono= WebClient.builder()
                .baseUrl("https://api.ip2country.info")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ip")
                        .query(ip.trim())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseIpGeolocation.class);

        return mono.block();
    }

    public ResponseCountryInfo getCountryInfo(String countryCode){
        log.info("IP INFO - GeneralWebClient - getCountryInfo");

        Mono<ResponseCountryInfo> mono= WebClient.builder()
                .baseUrl("https://restcountries.eu/rest/v2")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/alpha/"+countryCode.trim())
                        .queryParam("fields", "name;cioc;currencies;languages;timezones;latlng")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseCountryInfo.class);

        return mono.block();
    }

    public CurrencyExchangeRates getExchangeRates(String apiAccessKey, String currencyCode){
        log.info("IP INFO - GeneralWebClient - getExchangeRates");

        Mono<CurrencyExchangeRates> mono= WebClient.builder()
                .baseUrl("http://data.fixer.io")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/latest")
                        .queryParam("base", currencyCode.trim())
                        .queryParam("access_key",apiAccessKey.trim())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrencyExchangeRates.class);

        return mono.block();
    }

}
