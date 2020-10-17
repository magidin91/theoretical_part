package ru.education.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.education.entity.Product;
import ru.education.entity.SalesPeriods;
import ru.education.entity.SalesPeriodsJDBCDemo;
import ru.education.jdbc.SalesProductJDBCRepository;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesPeriodsRepository;
import ru.education.model.Formatter;

import java.util.List;

@RestController
@RequestMapping("/api/v1") //обращаемся по маппингу - "/api/v1"
public class TestController {
    //используем доступ к бд без сервиса, чтобы протестровать
    private final ProductRepository productRepository;

    private final SalesProductJDBCRepository salesProductJDBCRepository;

    private final SalesPeriodsRepository salesPeriodsRepository;

    @Qualifier("fooFormatter") //инджектит конкретный бин, в этом случае -"fooFormatter"
    private final Formatter formatter;

    public TestController(Formatter formatter, ProductRepository productRepository, SalesProductJDBCRepository salesProductJDBCRepository, SalesPeriodsRepository salesPeriodsRepository) {
        this.formatter = formatter;
        this.productRepository = productRepository;
        this.salesProductJDBCRepository = salesProductJDBCRepository;
        this.salesPeriodsRepository = salesPeriodsRepository;
    }

    @GetMapping("/hello") //обращаемся по методу get, mapping - "/hello"
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/format")
    public String getFormat() {
        return formatter.format();
    }

    //выводим таблицу product
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/sales/count")
    public Integer getSalesCount() {
        return salesProductJDBCRepository.count();
    }

    @GetMapping("/sales")
    public List<SalesPeriodsJDBCDemo> getSalesPeriods() {
        return salesProductJDBCRepository.getSalesPeriods();
    }

    @GetMapping("/sales/byhigherprice")
    public List<SalesPeriodsJDBCDemo> getSalesPeriodsPriceIsHigher() {
        return salesProductJDBCRepository.getSalesPeriodsPriceIsHigher(90);
    }

    @GetMapping("/products/sales/active")
    public List<Product> getProductsWithActivePeriods() {
        return salesProductJDBCRepository.getProductsWithActivePeriods();
    }

    @GetMapping("/sales/jpa")
    public List<SalesPeriods> getSalesPeriodsJpa() {
        return salesPeriodsRepository.findAll();
    }

    @PostMapping("/sales/jpa")
    public SalesPeriods addSalesPeriodsJpa(@RequestBody SalesPeriods salesPeriods) {
        return salesPeriodsRepository.save(salesPeriods);
    }

    @GetMapping("/sales/jpa/max/price")
    public Integer getMaxPriceByProductId() {
        return salesPeriodsRepository.getMaxPriceByProductId(1);
    }

    @GetMapping("/sales/jpa/exist/price")
    public boolean existsByPrice() {
        return salesPeriodsRepository.existsByPrice(60);
    }

    @GetMapping("/sales/jpa/active")
    List<SalesPeriods> findByDateToIsNull() {
        return salesPeriodsRepository.findByDateToIsNull();
    }

    @GetMapping("/sales/jpa/byproductname")
    List<SalesPeriods> findByProductName(String productName){
        return salesPeriodsRepository.findByProductName("bike");
    }
}
