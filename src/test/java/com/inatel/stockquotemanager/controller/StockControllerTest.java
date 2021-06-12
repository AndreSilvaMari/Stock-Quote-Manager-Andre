package com.inatel.stockquotemanager.controller;

import com.inatel.stockquotemanager.model.Stock;
import com.inatel.stockquotemanager.model.StockQuote;
import com.inatel.stockquotemanager.service.StockQuoteService;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockQuoteService stockQuoteService;

    private List<StockQuote> stockQuoteList;

    private List<Stock> stockList;

    private Stock stock;

    @BeforeEach
    public void beforeEach() throws ParseException {
        stockQuoteList = new ArrayList<>();
        stockList = new ArrayList<>();

        stock = new Stock("vale5", stockQuoteList, new JSONObject());
        stockList.add(stock);

        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2021-06-01");
        StockQuote stockQuote = new StockQuote(date, 150.00, stock);
        stockQuoteList.add(stockQuote);

        Mockito.when(stockQuoteService.readAllStockQuotes()).thenReturn(stockList);
    }

    @Test
    public void shouldReturnListStock() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/stock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldReturnStockById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/stock?id=vale5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldPostStock() throws Exception {
        stock.setStockQuoteList(null);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/stock")
                        .content(stock.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
