package bdd.demo.appjava.api.pub.auth;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@lombok.AllArgsConstructor
@lombok.Builder
@lombok.Data
@lombok.NoArgsConstructor
public class RegisterRequest {
    @Schema(description = "A valid email address used as username.", example = "abc@test.com")
    @NotBlank @Email
    @Size(min = 2, max = 20)
    private String username;

    @Schema(description = "Users full name", example = "Joe Bloggs")
    @NotBlank
    private String fullName;

    @Schema(description = "Users password")
    @NotBlank(message = "{password.notblank}")
    private String password;

    @Schema(description = "Re-type users password. Must match the password.")
    @NotBlank
    private String rePassword;
    
    private Set<String> authorities;
}
