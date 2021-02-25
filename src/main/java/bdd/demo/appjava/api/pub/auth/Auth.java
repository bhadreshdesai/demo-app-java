package bdd.demo.appjava.api.pub.auth;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "/api/pub/auth")
public class Auth {
    private static final Logger LOG = LoggerFactory.getLogger(Auth.class);

    @Operation(summary = "Register a user", description = "Register a new user in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully registered"), 
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    @PostMapping(value="register", produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
        LOG.info("Auth.register called");
        return RegisterResponse.builder().username(request.getUsername()).fullName(request.getFullName()).build();
    }
}
