package com.mercadolibre.ipinfo;

import com.mercadolibre.ipinfo.schedule.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private ScheduleTask scheduleTask;

    @Value("${currency.process_active}")
    private boolean process_active;

    @Override
    public void run(String...args) {
        if(this.process_active){
            scheduleTask.currencyExchangeTask();
        }
    }
}
