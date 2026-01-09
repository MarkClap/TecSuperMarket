package TecSupermarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfficeRequest {
    @NotBlank(message = "Name obligate")
    private String name;

    @NotBlank(message = "Direction obligate")
    private String direction;
}
