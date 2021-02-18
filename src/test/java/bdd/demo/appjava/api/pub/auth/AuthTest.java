package bdd.demo.appjava.api.pub.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
    private static final Logger LOG = LoggerFactory.getLogger(AuthTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        AuthRequest authRequest = AuthRequest.builder().build();
        MvcResult registerResult = this.mockMvc.perform(post("/api/pub/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk()).andReturn();
        String response = registerResult.getResponse().getContentAsString();
        LOG.info("registerResult response: " + response);
    }
}
