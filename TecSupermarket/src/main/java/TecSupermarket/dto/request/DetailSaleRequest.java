package TecSupermarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailSaleRequest {
    @NotBlank(message = "Product name required")
    private String nameProd;

    @NotNull(message = "Stock required")
    @Positive(message = "Stock must be greater than 0")
    private Integer stockProd;
}
