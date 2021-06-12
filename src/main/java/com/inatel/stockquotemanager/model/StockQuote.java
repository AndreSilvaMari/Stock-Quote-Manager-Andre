package com.inatel.stockquotemanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "stockquote")
public class StockQuote extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @Column(name = "quotation_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date quotationAt;

    @Column(name = "stock_price", precision = 2)
    private Double stockPrice;

    @ManyToOne
    @JoinColumn(name = "stock")
    @JsonBackReference
    private Stock stock;

    public StockQuote(Date quotationAt, Double stockPrice, Stock stock) {
        this.quotationAt = quotationAt;
        this.stockPrice = stockPrice;
        this.stock = stock;
    }
}
