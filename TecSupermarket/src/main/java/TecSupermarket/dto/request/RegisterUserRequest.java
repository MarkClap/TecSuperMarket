package TecSupermarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank(message = "Email required")
    public String email;

    @NotBlank(message = "Password required")
    public String password;
}
