package service;

import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.education.entity.Product;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.service.impl.DefaultProductService;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class DefaultProductServiceTest {
    @Autowired
    private DefaultProductService defaultProductService;

    @Test
    public void findAll() {
        List<Product> products = defaultProductService.findAll();
        assertEquals(2, products.size());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNUllProductAndGetEntityIllegalArgumentException() {
        defaultProductService.create(null);
    }
}
