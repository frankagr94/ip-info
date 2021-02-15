package com.mercadolibre.ipinfo.schedule;

import com.mercadolibre.ipinfo.service.ScheduleTaskService;
import com.mercadolibre.ipinfo.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired

    private ScheduleTaskService scheduleTaskService;

    @Scheduled(cron = "${cron.currency_exchange}")
    public void currencyExchangeTask() {
        logger.info("Currency Exchange Job :: STARTED at {}", Util.getDatetimeByTimeZone(ZoneId.systemDefault().getId()));

        try{
            scheduleTaskService.currencyExchangeUpdate();
        }catch(Exception e){
            logger.error("Currency Exchange Job :: EXECUTION ERROR: %s", e);
        }

        logger.info("Currency Exchange Job :: FINISHED at {}", Util.getDatetimeByTimeZone(ZoneId.systemDefault().getId()));
    }

}
