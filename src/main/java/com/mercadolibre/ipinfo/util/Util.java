package com.mercadolibre.ipinfo.util;

import com.mercadolibre.ipinfo.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;

public class Util {

    private final static String DATE_TIME_PATTERN = "dd/MM/yyyy hh:mm:ss a";

    private final static String TIME_PATTERN = "hh:mm:ss a";

    private static Response response = new Response();

    public static LinkedHashMap CURRENCY_EXCHANGE_RATES;

    public static ResponseEntity<Object> getReturnController(Response response){
        if(!response.isResponse()){
            if(!response.isException()){
                return new ResponseEntity<>(new ResponseDTO(false,response.getMessage(),null), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseDTO(false,response.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ResponseDTO(true,response.getMessage(),response.getObject()), HttpStatus.OK);
    }

    public static Response getResponseFromService(String message, Object object, Class<?> clazz, int resultType){
        Logger log = LoggerFactory.getLogger(clazz);

        if(resultType == 1){
            log.warn(message);
            response.failed(message,false);
            return response;
        }

        if(resultType == 2){
            log.error(message);
            response.failed(message,true);
            return response;
        }

        log.info(message);
        response.success(message,object);

        return response;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            var theta = lon1 - lon2;

            var dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));

            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            if (unit.equals("KM")) {
                dist *= 1.609344;
            } else if (unit.equals("NM")) {
                dist *= 0.8684;
            }
            return Math.round(dist);
        }
    }

    public static String getCurrentDate(){
        var format = new SimpleDateFormat(DATE_TIME_PATTERN);

        return format.format(new Date());
    }

    public static String getDatetimeByTimeZone(String timezone){
        var zone = ZoneId.of(timezone);

        return ZonedDateTime.now(zone).format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

}
