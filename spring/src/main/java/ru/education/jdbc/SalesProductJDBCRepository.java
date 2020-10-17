package ru.education.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.education.entity.Product;
import ru.education.entity.SalesPeriodsJDBCDemo;

import java.util.List;

@Repository
public class SalesProductJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

    public SalesProductJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from sales_periods", Integer.class);
        //Выполняет запрос и преобразует результат к определенному классу
    }

    public List<SalesPeriodsJDBCDemo> getSalesPeriods() {
        return jdbcTemplate.query("select* from sales_periods", (rs, rowNum) -> new SalesPeriodsJDBCDemo(
                rs.getInt("id"),
                rs.getLong("price"),
                rs.getDate("date_from"),
                rs.getDate("date_to"),
                rs.getInt("product")
        ));
    }

    public List<SalesPeriodsJDBCDemo> getSalesPeriodsPriceIsHigher(long price) {
        return jdbcTemplate.query(String.format("select* from sales_periods where price>=%s", price),
                (rs, rowNum) -> new SalesPeriodsJDBCDemo(
                        rs.getInt("id"),
                        rs.getLong("price"),
                        rs.getDate("date_from"),
                        rs.getDate("date_to"),
                        rs.getInt("product")
                ));
    }

    public List<Product> getProductsWithActivePeriods() {
        return jdbcTemplate.query(
                "Select p.id, p.name from product as p Inner join sales_periods as sp " +
                        "On p.id = sp.product where sp.date_to is null",
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
