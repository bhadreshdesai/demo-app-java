package bdd.demo.appjava.api.pub.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
public class RegisterResponse {
    @NotBlank @Email
    private String username;
    @NotBlank
    private String fullName;
}
