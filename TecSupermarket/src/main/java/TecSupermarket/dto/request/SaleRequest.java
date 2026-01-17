package TecSupermarket.dto.request;

import TecSupermarket.dto.response.DetailSaleResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    private String state;
    private Long idOffice;
    private String userEmail;
    private List<DetailSaleRequest> details;
}
