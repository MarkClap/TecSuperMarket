package TecSupermarket.service;

import TecSupermarket.dto.ProductDTO;
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
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .category(productDTO.getCategory())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();

        return Mapper.toDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

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
