package TecSupermarket.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest {
    @Email
    @NotBlank(message = "Email required")
    public String email;

    @NotBlank(message = "Name required")
    public String password;
}
