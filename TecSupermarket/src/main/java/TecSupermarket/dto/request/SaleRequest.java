package TecSupermarket.dto.request;

import TecSupermarket.dto.response.DetailSaleResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    @NotBlank
    private String state;

    @Positive(message = "Office required")
    private Long idOffice;

    private List<DetailSaleRequest> details;
}
