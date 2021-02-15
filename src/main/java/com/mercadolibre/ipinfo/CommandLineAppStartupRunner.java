package com.mercadolibre.ipinfo;

import com.mercadolibre.ipinfo.schedule.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private ScheduleTask scheduleTask;

    @Override
    public void run(String...args) {
        scheduleTask.currencyExchangeTask();
    }
}
