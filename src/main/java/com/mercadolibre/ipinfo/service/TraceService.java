package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.dto.RequestTrace;
import com.mercadolibre.ipinfo.dto.ResponseTrace;
import com.mercadolibre.ipinfo.integration.ResponseCountryInfo;
import com.mercadolibre.ipinfo.util.Response;
import com.mercadolibre.ipinfo.util.Util;
import com.mercadolibre.ipinfo.webclient.GeneralWebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TraceService {

    private static final Logger log = LoggerFactory.getLogger(TraceService.class);

    @Autowired
    private GeneralWebClient generalWebClient;

    @Autowired
    private CountryStatInfoService countryStatInfoService;

    @Value("${city.latitude}")
    private String cityLatitude;

    @Value("${city.longitude}")
    private String cityLongitude;

    @Value("${city.measure_unit}")
    private String measureUnit;

    @Value("${currency.exchange}")
    private String currencyToExchange;

    /**
     * traceIP Method
     *
     * It's in charged of consulting and retrieving all the info about the IP
     * @param requestTrace with the IP
     * @return Response class with message, result and ResponseTrace with the info
     */
    public Response traceIP(RequestTrace requestTrace){
        log.info("IP INFO - TraceService - traceIP");

        try{
            if(requestTrace == null){
                return Util.getResponseFromService("Request Body can not be null",null,TraceService.class,1);
            }

            if(requestTrace.getIp() == null){
                return Util.getResponseFromService("The IP field can not be null",null,TraceService.class,1);
            }

            if(requestTrace.getIp().trim().isEmpty()){
                return Util.getResponseFromService("The IP field from the Request Body, can not be empty",null,TraceService.class,1);
            }

            var geo = generalWebClient.getIpGeolocationInfo(requestTrace.getIp().trim());

            if(geo == null){
                return Util.getResponseFromService("IP Information Not Found",null,TraceService.class,1);
            }

            var countryInfo = generalWebClient.getCountryInfo(geo.getCountryCode().trim());

            if(countryInfo == null){
                return Util.getResponseFromService("Country Information with Code "+geo.getCountryCode()+" not found",null,TraceService.class,1);
            }

            return Util.getResponseFromService("OK",mappingResponseTrace(requestTrace.getIp(),countryInfo),TraceService.class,0);
        }catch(Exception e){
            return Util.getResponseFromService(String.format("An execution error has ocurred, please contact to the admin : %s", e),null,TraceService.class,2);
        }
    }

    /**
     * mappingResponseTrace Method
     *
     * It's in charged of mapping on a ResponseTrace instance all the info got
     * @param ip consulted
     * @param respCountryInfo with the ip country info
     * @return ResponseTrace class with the endpoint info to return
     */
    private ResponseTrace mappingResponseTrace(String ip, ResponseCountryInfo respCountryInfo){
        var responseTrace = new ResponseTrace();

        var distance = Util.distance(Double.valueOf(cityLatitude),Double.valueOf(cityLongitude),
                Double.valueOf(respCountryInfo.getLatlng().get(0)),Double.valueOf(respCountryInfo.getLatlng().get(1)),measureUnit);

        responseTrace.setIp(ip.trim());
        responseTrace.setDate(Util.getCurrentDate());
        responseTrace.setCountry(respCountryInfo.getName());
        responseTrace.setIso_code(respCountryInfo.getCioc());
        responseTrace.setLanguages(respCountryInfo.getLanguages().stream()
                .map(language -> language.getName()+" ("+language.getIso639_1()+")")
                .collect(Collectors.toList()));
        responseTrace.setCurrency(respCountryInfo.getCurrencies().stream()
                .map(currency -> currency.getName()+" ("+currency.getCode()+")"+getCurrencyExchage(currency.getCode()))
                .collect(Collectors.toList()));
        responseTrace.setTimes(respCountryInfo.getTimezones().stream()
                .map(timezone -> Util.getDatetimeByTimeZone(timezone)+" ("+timezone+")")
                .collect(Collectors.toList()));
        responseTrace.setEstimated_distance(distance + " " + measureUnit);

        countryStatInfoService.saveUpdate(responseTrace.getIso_code(),responseTrace.getCountry(), distance);

        return responseTrace;
    }

    /**
     * getCurrencyExchage Method
     *
     * It's in charged of searching the exchange in the list of exchanges to find the currency amount
     * @param currencyCode country currency code
     * @return String chain with the exchange message or empty if not found
     */
    private String getCurrencyExchage(String currencyCode){
        if(Util.CURRENCY_EXCHANGE_RATES == null){
            return "";
        }

        if(!Util.CURRENCY_EXCHANGE_RATES.containsKey(currencyCode.trim())){
            return "";
        }

        return " - 1 "+currencyToExchange.trim()+" = "+Double.parseDouble(Util.CURRENCY_EXCHANGE_RATES.get(currencyCode.trim()).toString())+" "+currencyCode;
    }

}
