package TecSupermarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Category required")
    private String category;

    @NotBlank(message = "Price required")
    private Double price;
    private int stock;
}
