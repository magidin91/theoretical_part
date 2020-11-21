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
@SpringBootTest(classes = {ProductController.class, MockProductService.class}) //используем заглушку для сервиса
/* здесь не указываем @ContextConfiguration, т.к. указали тестируемый класс и компонент, который инджектится - MockProductService, в SpringBootTest */
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    MockMvc mockMvc; // mockMvc используется для вызова http-запросов

    private final static String URL = "http://localhost:8080/api/v1/product"; //для удобства создали константу с URL

    /* т.к. мы конструируем запросы вручную, то в тестах маппинг по умолчнию не отрабатывает, поэтому мы явно используем jackson - маппер */
     ObjectMapper mapper = new ObjectMapper();

    /**
     * инициализирует  mockMvc
     */
    @Before
    public void setUp() {
        /* standaloneSetup(productController) - создает экземпляр MockMvc, зарегистрировав один или
        несколько контроллеров, и позволяет вручную настроить инфраструктуру Spring MVC для создания http-запросов */
        this.mockMvc = standaloneSetup(productController).build();
    }

    /**
     * проверяем, что в методе findAll возвращается правильный статус
     */
    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL)).andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Product product = new Product(3, "productTest");
        String requestJson = mapper.writeValueAsString(product); // сериализуем Product объект в строку в json формате.
        // System.out.println(requestJson); // {"id":3,"name":"productTest"}
        mockMvc.perform(
                post(URL).
                        contentType(MediaType.APPLICATION_JSON_UTF8).
                        content(requestJson)
        ).andExpect(status().isCreated());
    }
}
