package repository;

import config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.education.entity.Product;
import ru.education.jpa.ProductRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class}) //определяет, где какие бины искать для запуска тестов в этом классе
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository; //изначально будет подчеркиваться, потом подтянет бины

    @Before
    public void createProduct() {
        Product product = new Product(3, "product_test");
        productRepository.save(product);
    }

    @Test
    public void findAll() {
        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());
    }

    @Test
    public void findByName() {
        List<Product> products = productRepository.findByName("product_test");
        assertEquals(1, products.size());
    }

    @After
    public void deleteProduct() {
        productRepository.deleteById(3);
        assertEquals(2,   productRepository.findAll().size());
    }
}