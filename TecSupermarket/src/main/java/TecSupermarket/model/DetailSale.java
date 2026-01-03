package TecSupermarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Sales
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "saleId")
    private Sale sale;

    //Product
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;
    private Integer stockProd;
    private Double price;

    //User
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;
    private String email;
}
