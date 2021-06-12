package com.inatel.stockquotemanager.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inatel.stockquotemanager.dto.StockDTO;
import com.inatel.stockquotemanager.model.Stock;
import com.inatel.stockquotemanager.model.StockQuote;
import com.inatel.stockquotemanager.repository.StockQuoteRepository;
import com.inatel.stockquotemanager.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockQuoteService{

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockQuoteRepository stockQuoteRepository;

    public void createStockQuote(Stock stock, List<StockDTO> stockDTOList) throws JsonProcessingException, ParseException {
        assert stockDTOList != null;
        boolean stockIsRegistered = stockDTOList.stream().filter(o -> o.getId().equals(stock.getId())).findFirst().isPresent();
       if(stockIsRegistered) {
           stockRepository.save(stock);
           if(!Objects.isNull(stock.getQuotes()))
               registerStockQuotes(stock);
       }
       else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Stock not registered at Stock-Manager");
       }
    }

    public Stock readStockQuoteById(String id){
        return stockRepository.findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }

    public List<Stock> readAllStockQuotes(){
        return stockRepository.findAll();
    }

    public void registerStockQuotes(Stock stock) throws JsonProcessingException, ParseException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(stock.getQuotes().toString());
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(field.getKey());
            StockQuote stockQuote = new StockQuote(date, field.getValue().asDouble(), stock);
            stockQuoteRepository.save(stockQuote);
        }
    }

}
