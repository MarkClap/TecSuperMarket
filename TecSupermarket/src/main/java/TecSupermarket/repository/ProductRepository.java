package TecSupermarket.repository;

import TecSupermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Search product by name
    Optional<Product> findByName(String name);
}
