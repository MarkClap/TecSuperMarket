package TecSupermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthCheckResponse {
    private String email;
    private String role;
}
