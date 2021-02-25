package bdd.demo.appjava.api.pub.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {
    @NotBlank @Email
    private String username;
    @NotBlank
    private String fullName;
}
