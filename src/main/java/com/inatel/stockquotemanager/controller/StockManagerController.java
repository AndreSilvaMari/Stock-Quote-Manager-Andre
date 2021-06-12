package com.inatel.stockquotemanager.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
@CrossOrigin()
public class StockManagerController {

    @DeleteMapping
    @CacheEvict(value = "responseStock")
    public ResponseEntity<?> cacheEviction(){
        try {
            return ResponseEntity.ok("Cache evicted successfuly!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
