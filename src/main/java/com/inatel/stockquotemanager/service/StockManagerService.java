package com.inatel.stockquotemanager.service;

import com.inatel.stockquotemanager.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StockManagerService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    public RestTemplate restTemplate;

    protected final String URL_Stock_manager = "http://localhost:8080";

    @Cacheable(value = "responseStock")
    public List<StockDTO> checkManagerStocks(){
        ResponseEntity<List<StockDTO>> responseStock = restTemplate.exchange(
                URL_Stock_manager + "/stock",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StockDTO>>() {
                });
        return responseStock.getBody();
    }

    @PostConstruct
    public void registerOnStockManager(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("{\"host\": \"localhost\",\"port\": 8081}", headers);
        String response = restTemplate.postForObject(URL_Stock_manager+"/notification", entity, String.class);
        System.out.println(response);
    }
}
