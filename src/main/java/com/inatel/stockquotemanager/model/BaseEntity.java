package com.inatel.stockquotemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Column(name = "creation_date", updatable = false)
    @DateTimeFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    @CreationTimestamp
    @JsonIgnore
    private Date creationDate;


    @Column(name = "update_date")
    @DateTimeFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    @UpdateTimestamp
    @JsonIgnore
    private Date updateDate;

}
