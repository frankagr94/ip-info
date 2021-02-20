package com.mercadolibre.ipinfo.service;

import com.mercadolibre.ipinfo.model.CountryStatInfo;
import com.mercadolibre.ipinfo.repository.CountryStatInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CountryStatInfoService {

    private static final Logger log = LoggerFactory.getLogger(CountryStatInfoService.class);

    @Autowired
    private CountryStatInfoRepository countryStatInfoRepository;

    @Async("threadPoolTaskExecutor")
    public void saveUpdate(String isoCode, String countryName, Double distance){
        log.info("IP INFO - CountryStatInfoService - saveUpdate");

        try {
            var countryStatInfo = countryStatInfoRepository.findById(isoCode.trim()).orElse(null);

            if(countryStatInfo == null){
                countryStatInfo = new CountryStatInfo(isoCode.trim(),countryName.trim(),distance,0);
            }

            countryStatInfo.setInvocations(countryStatInfo.getInvocations() + 1);

            countryStatInfoRepository.save(countryStatInfo);
        }catch (Exception e){
            log.error("An execution error has ocurred, please contact to the admin : %s", e);
        }
    }

}
