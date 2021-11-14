package via.sep4.data.webapi;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import via.sep4.data.webapi.service.SensorService;

@SpringBootTest
class WebapiApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private SensorService sensorService;

    @BeforeEach
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        try {
            sensorService.addTemperature("32");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void contextLoads() throws Exception {
        try {
            Assert.assertNotNull(sensorService.findById(1));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
