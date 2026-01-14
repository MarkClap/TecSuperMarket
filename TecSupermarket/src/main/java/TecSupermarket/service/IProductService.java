package TecSupermarket.service;

import TecSupermarket.dto.request.ProductRequest;
import TecSupermarket.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getProducts();
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
}
