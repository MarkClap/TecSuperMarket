package TecSupermarket.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private String state;
    private Long idOffice;
    private String userEmail;
    private List<DetailSaleDTO> details;

    private Double total;
}
