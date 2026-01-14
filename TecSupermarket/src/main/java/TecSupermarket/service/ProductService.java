package TecSupermarket.service;

import TecSupermarket.dto.request.ProductRequest;
import TecSupermarket.dto.response.ProductResponse;
import TecSupermarket.exception.NotFoundException;
import TecSupermarket.mapper.Mapper;
import TecSupermarket.model.Product;
import TecSupermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getStock()
                ))
                .toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();

        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found"));
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product cannot be deleted because the ID wasn't found");
        }
        productRepository.deleteById(id);
    }
}
