package TecSupermarket.mapper;

import TecSupermarket.dto.DetailSaleDTO;
import TecSupermarket.dto.SaleDTO;
import TecSupermarket.dto.response.OfficeResponse;
import TecSupermarket.dto.response.ProductResponse;
import TecSupermarket.model.Office;
import TecSupermarket.model.Product;
import TecSupermarket.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    // Map Product to ProductResponse
    public static ProductResponse toDTO(Product product){
        if (product == null) return null;

        return new  ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
        );
    }

    // Map Sale to SaleDTO
    public static SaleDTO toDTO(Sale sale){
        if (sale == null) return null;
        var detail = sale.getDetail().stream().map(detailSale ->
                DetailSaleDTO.builder()
                        .id(detailSale.getProduct().getId())
                        .nameProd(detailSale.getProduct().getName())
                        .stockProd(detailSale.getStockProd())
                        .price(detailSale.getPrice())
                        .subtotal(detailSale.getPrice() * detailSale.getStockProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detail.stream()
                .map(DetailSaleDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .idOffice(sale.getOffice().getId())
                .state(sale.getState())
                .userEmail(sale.getUser().getEmail())
                .details(detail)
                .total(total)
                .build();
    }

    // Map Office to OfficeResponse
    public static OfficeResponse toDTO(Office office){
        if (office == null) return null;
        return new OfficeResponse(
                office.getId(),
                office.getName(),
                office.getDirection()
        );
    }
}
