package via.sep4.data.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import via.sep4.data.webapi.service.measurement.MeasurementService;

@SpringBootTest
class WebApiApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private MeasurementService measurementService;

    @BeforeEach
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
}