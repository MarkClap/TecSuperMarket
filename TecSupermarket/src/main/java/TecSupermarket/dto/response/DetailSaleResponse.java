package TecSupermarket.dto.response;

public record DetailSaleResponse(
        Long id,
        String nameProd,
        Integer stockProd,
        Double price,
        Double subtotal
) {
}
