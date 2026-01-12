package TecSupermarket.service;

import TecSupermarket.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
