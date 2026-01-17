package TecSupermarket.mapper;

import TecSupermarket.dto.response.DetailSaleResponse;
import TecSupermarket.dto.response.OfficeResponse;
import TecSupermarket.dto.response.ProductResponse;
import TecSupermarket.dto.response.SaleResponse;
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
    public static SaleResponse toDTO(Sale sale){
        if (sale == null) return null;
        var detail = sale.getDetail().stream().map(detailSale ->
                new DetailSaleResponse(
                        detailSale.getId(),
                        detailSale.getProduct().getName(),
                        detailSale.getStockProd(),
                        detailSale.getPrice(),
                        detailSale.getPrice()* detailSale.getStockProd()
                )
        ).toList();

        return new SaleResponse(
                sale.getId(),
                sale.getDate(),
                sale.getState(),
                sale.getOffice().getId(),
                sale.getUser().getEmail(),
                detail,
                sale.getTotal()
        );
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
