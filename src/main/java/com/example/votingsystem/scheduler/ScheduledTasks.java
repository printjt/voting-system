package com.example.votingsystem.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Scheduled(fixedDelay = 86400000)
    public void test(){
        System.out.println("hello " + System.currentTimeMillis());
    }
}
