package com.inatel.stockquotemanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONObject;


import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "stock")
public class Stock extends BaseEntity{

        @Id
        @Column(name = "id")
        private String id;

        @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<StockQuote> stockQuoteList;

        @Transient
        private JSONObject quotes;

}
