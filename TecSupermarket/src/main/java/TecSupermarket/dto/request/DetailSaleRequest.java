package TecSupermarket.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailSaleRequest {
    private String nameProd;
    private Integer stockProd;
    private Double price;
    private Double subtotal;
}
