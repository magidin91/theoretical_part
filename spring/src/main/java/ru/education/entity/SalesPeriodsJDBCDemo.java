package ru.education.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalesPeriodsJDBCDemo {
    private int id;
    private long price;
    private Date dateFrom;
    private Date dateTo;
    private Integer product;

    public SalesPeriodsJDBCDemo(int id, long price, Date dateFrom, Date dateTo, Integer product) {
        this.id = id;
        this.price = price;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.product = product;
    }
}
