package TecSupermarket.mapper;

import TecSupermarket.dto.DetailSaleDTO;
import TecSupermarket.dto.OfficeDTO;
import TecSupermarket.dto.ProductDTO;
import TecSupermarket.dto.SaleDTO;
import TecSupermarket.model.Office;
import TecSupermarket.model.Product;
import TecSupermarket.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    // Map Product to ProductDTO
    public static ProductDTO toDTO(Product product){
        if (product == null) return null;

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
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
                .details(detail)
                .total(total)
                .build();
    }

    // Map Office to OfficeDTO
    public static OfficeDTO toDTO(Office office){
        if (office == null) return null;
        return OfficeDTO.builder()
                .id(office.getId())
                .name(office.getName())
                .direction(office.getDirection())
                .build();
    }
}
