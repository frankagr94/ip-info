package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.dto.ResponseStats;
import com.mercadolibre.ipinfo.model.CountryStatInfo;
import com.mercadolibre.ipinfo.util.Response;
import com.mercadolibre.ipinfo.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StatsService {

    private static final Logger log = LoggerFactory.getLogger(StatsService.class);

    @Autowired
    private CountryStatInfoService countryStatInfoService;

    @Value("${city.measure_unit}")
    private String measureUnit;

    /**
     * getStats Method
     *
     * It's in charged of consulting and retrieving some stats about the all the succeeded executions
     * It returns, Farthest Distance and Nearest Distance (Country name, distance, executions number),
     * Average Distance of all executions
     * @return Response class with message, result and ResponseTrace with the info
     */
    public Response getStats(){
        log.info("IP INFO - StatsService - getStats");

        try{
            var dataExecutions = countryStatInfoService.getAll();

            if(dataExecutions.isEmpty()){
                return Util.getResponseFromService("No data to show",null,StatsService.class,0);
            }

            return Util.getResponseFromService("OK",mappingResponseStats(dataExecutions),StatsService.class,0);
        }catch(Exception e){
            return Util.getResponseFromService(String.format("An execution error has ocurred, please contact to the admin : %s", e),null,TraceService.class,2);
        }
    }

    /**
     * mappingResponseStats Method
     *
     * It's in charged of mapping on a ResponseStats instance all the info to return using lambdas to calculate
     * @param countryStatInfos list of data retrieved from data base
     * @return ResponseStats class with the data info to return
     */
    private ResponseStats mappingResponseStats(List<CountryStatInfo> countryStatInfos){
        var responseStats = new ResponseStats();

        responseStats.setFarthest_distance_country(countryStatInfos.stream().parallel()
                .max(Comparator.comparing(CountryStatInfo::getDistance))
                .orElseThrow(NoSuchElementException::new));

        responseStats.setNearest_distance_country(countryStatInfos.stream().parallel()
                .min(Comparator.comparing(CountryStatInfo::getDistance))
                .orElseThrow(NoSuchElementException::new));

        responseStats.setAverage_distance(Math.floor(countryStatInfos.stream().parallel()
                .mapToDouble(csi -> csi.getDistance() * csi.getInvocations())
                .sum() / countryStatInfos.stream().parallel().mapToInt(CountryStatInfo::getInvocations).sum())
                + " " + measureUnit);

        return responseStats;
    }

}
