package bdd.demo.appjava.api.pub.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        RegisterRequest authRequest = RegisterRequest.builder().username("user@name.com").fullName("Full Name").password("password").rePassword("password").build();
        MvcResult registerResult = this.mockMvc.perform(post("/api/pub/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk()).andReturn();
        String response = registerResult.getResponse().getContentAsString();
        LOG.info("testRegisterSuccess response: " + response);
    }

    @Test
    public void testRegisterInvalidRequest() throws Exception {
        RegisterRequest authRequest = RegisterRequest.builder().username("invalid_email").fullName("Full Name").password("password").rePassword("password").build();
        MvcResult registerResult = this.mockMvc.perform(post("/api/pub/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isBadRequest()).andReturn();

        Exception exception = registerResult.getResolvedException();
        assertNotNull(exception);
        assertTrue(exception instanceof MethodArgumentNotValidException);

        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        assertEquals(1, errors.size());
        assertEquals("must be a well-formed email address", errors.get(0).getDefaultMessage());
    }

    @Test
    public void testRegisterInvalidRequest_ES() throws Exception {
        RegisterRequest authRequest = RegisterRequest.builder().username("invalid_email").fullName("Full Name").password("password").rePassword("password").build();
        MvcResult registerResult = this.mockMvc.perform(post("/api/pub/auth/register")
                .header("Accept-Language", "es-ES")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isBadRequest()).andReturn();

        Exception exception = registerResult.getResolvedException();
        assertNotNull(exception);
        assertTrue(exception instanceof MethodArgumentNotValidException);

        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        assertEquals(1, errors.size());
        assertEquals("debe ser una dirección de correo electrónico con formato correcto", errors.get(0).getDefaultMessage()); 
    }

    @Test
    public void testRegisterInvalidPasswordRequest_ES() throws Exception {
        RegisterRequest authRequest = RegisterRequest.builder().username("a@b.com").fullName("Full Name").password("").rePassword("password").build();
        MvcResult registerResult = this.mockMvc.perform(post("/api/pub/auth/register")
                .header("Accept-Language", "es-ES")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isBadRequest()).andReturn();

        Exception exception = registerResult.getResolvedException();
        assertNotNull(exception);
        assertTrue(exception instanceof MethodArgumentNotValidException);

        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        assertEquals(1, errors.size());
        assertEquals("La contraseña no puede estar en blanco", errors.get(0).getDefaultMessage());
    }
}
