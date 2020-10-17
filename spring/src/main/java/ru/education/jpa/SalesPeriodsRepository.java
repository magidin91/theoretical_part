package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.education.entity.Product;
import ru.education.entity.SalesPeriods;

import java.util.List;

@Repository
public interface SalesPeriodsRepository extends JpaRepository<SalesPeriods, Integer> {
    /**
     * выполняем Query с параметром :productId, который берем из параметра метода @Param("productId") int productId
     */
    @Query(value = "select max(price) from sales_periods where product = :productId", nativeQuery = true)
    Integer getMaxPriceByProductId(@Param("productId") int productId);


    /**
     * запрос генерируется автоматически согласно названию и сигнатуре метода
     */
    boolean existsByPrice(long price);

    List<SalesPeriods> findByDateToIsNull();

    List<SalesPeriods> findByProductName(String productName);

    List<SalesPeriods> findByProduct(Product product);

    List<SalesPeriods> findByDateToIsNullAndProductId(Integer productId);
}
