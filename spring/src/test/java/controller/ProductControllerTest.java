package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.education.controllers.ProductController;
import ru.education.entity.Product;
import service.mock.MockProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductController.class, MockProductService.class})
//здесь не указываем @ContextConfiguration, т.к. указали точное расположение классов бинов выше
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/product";

    ObjectMapper mapper = new ObjectMapper(); // вместо преобразования jackson


    /**
     * инициализирует  mockMvc
     */
    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(productController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL)).andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Product product = new Product(3, "productTest");
        String requestJson = mapper.writeValueAsString(product); //сериализуем продукт из json в строку
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
        ).andExpect(status().isCreated());
    }
}
