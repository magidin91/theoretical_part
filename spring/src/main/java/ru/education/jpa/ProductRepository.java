package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.entity.Product;

import java.util.List;

/**
 * JPA репозиторий
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { //Integer - тип ключа id

    List<Product> findByName(String name);
}
