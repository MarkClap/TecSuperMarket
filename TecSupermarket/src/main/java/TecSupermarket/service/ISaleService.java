package TecSupermarket.service;

import TecSupermarket.dto.request.SaleRequest;
import TecSupermarket.dto.response.SaleResponse;
import java.util.List;

public interface ISaleService {
    List<SaleResponse> getSales();
    SaleResponse createSale(SaleRequest saleRequest);
    SaleResponse updateSale(Long id, SaleRequest saleRequest);
    void deleteSale(Long id);
}
