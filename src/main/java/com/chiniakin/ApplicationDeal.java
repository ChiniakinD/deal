package com.chiniakin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApplicationDeal {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDeal.class, args);
    }

}
