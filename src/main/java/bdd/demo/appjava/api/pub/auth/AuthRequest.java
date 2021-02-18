package bdd.demo.appjava.api.pub.auth;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest {
    @NotBlank @Email
    private String username;
    @NotBlank
    private String fullName;
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
    private Set<String> authorities;
}
