package com.inatel.stockquotemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StockquotemanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockquotemanagerApplication.class, args);
    }

}
