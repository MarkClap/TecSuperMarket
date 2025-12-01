package TecSupermarket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailSaleDTO {
    private Long id;
    private String nameProd;
    private Integer stockProd;
    private Double price;
    private Double subtotal;
}
