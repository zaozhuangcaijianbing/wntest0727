package com.wn.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestJob {

    @Scheduled(fixedRate = 2000)
    public void run(){
        System.out.println(new Date());
    }
}
