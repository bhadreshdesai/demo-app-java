package bdd.demo.appjava.api.pub.auth;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "/api/pub/auth")
public class Auth {
    private static final Logger LOG = LoggerFactory.getLogger(Auth.class);

    @PostMapping(value="register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody @Valid AuthRequest request) {
        LOG.info("Auth.register called");
        return "{\"msg\":\"User registered\"}";
    }
}
