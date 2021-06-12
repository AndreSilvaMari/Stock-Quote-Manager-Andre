package com.inatel.stockquotemanager.repository;

import com.inatel.stockquotemanager.model.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {

}
