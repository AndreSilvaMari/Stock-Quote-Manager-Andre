package com.inatel.stockquotemanager.controller;

import com.inatel.stockquotemanager.model.Stock;
import com.inatel.stockquotemanager.service.StockManagerService;
import com.inatel.stockquotemanager.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockQuoteService  stockQuoteService;

    @Autowired
    StockManagerService stockManagerService;

    @PostMapping
    public ResponseEntity<?> postStockQuote(
            @RequestBody Stock stock
    ){
        try{
            stockQuoteService.createStockQuote(stock,stockManagerService.checkManagerStocks());
            return ResponseEntity.ok().body("Stock Quote registered successfully!");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readStockQuoteById(
            @RequestParam("id") String id
    ){
        try{
            return ResponseEntity.ok(stockQuoteService.readStockQuoteById(id));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readAllStockQuotes(
    ){
        try{
            return ResponseEntity.ok(stockQuoteService.readAllStockQuotes());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
