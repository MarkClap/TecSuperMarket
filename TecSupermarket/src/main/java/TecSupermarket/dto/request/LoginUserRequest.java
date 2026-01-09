package TecSupermarket.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserRequest {
    public String email;
    public String password;
}
