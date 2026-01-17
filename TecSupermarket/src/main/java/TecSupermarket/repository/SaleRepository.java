package TecSupermarket.repository;

import TecSupermarket.model.Sale;
import TecSupermarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findSaleByUser(User user);
}
