package TecSupermarket.dto.response;

import java.time.LocalDate;
import java.util.List;

public record SaleResponse(
        Long id,
        LocalDate date,
        String state,
        Long idOffice,
        String userEmail,
        List<DetailSaleResponse> details,
        Double total
) {
}
