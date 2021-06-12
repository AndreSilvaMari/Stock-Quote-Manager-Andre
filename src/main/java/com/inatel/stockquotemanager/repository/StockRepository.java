package com.inatel.stockquotemanager.repository;

import com.inatel.stockquotemanager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
