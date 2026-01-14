package TecSupermarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeRequest {
    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Direction required")
    private String direction;
}
