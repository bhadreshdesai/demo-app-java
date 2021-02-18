package bdd.demo.appjava.api.pub.home;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeTest {
    private static final Logger LOG = LoggerFactory.getLogger(HomeTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public HomeTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testHomeAbout() throws Exception {
        MvcResult registerResult = this.mockMvc
                .perform(get("/api/pub/home/about"))
                .andExpect(status().isOk())
                .andReturn();
        String responseText = registerResult.getResponse().getContentAsString();
        LOG.info(responseText);
        HomeResponse homeResponse = objectMapper.readValue(responseText, HomeResponse.class);
        Assertions.assertEquals("1.0.0", homeResponse.getVersion());
        Assertions.assertEquals("Demo Java Application", homeResponse.getName());
        Assertions.assertEquals("DEMO-JAVA-APP", homeResponse.getShortname());
    }
}
