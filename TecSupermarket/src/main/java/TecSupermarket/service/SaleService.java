package TecSupermarket.service;

import TecSupermarket.dto.request.DetailSaleRequest;
import TecSupermarket.dto.request.SaleRequest;
import TecSupermarket.dto.response.SaleResponse;
import TecSupermarket.exception.NotFoundException;
import TecSupermarket.mapper.Mapper;
import TecSupermarket.model.*;
import TecSupermarket.repository.OfficeRepository;
import TecSupermarket.repository.ProductRepository;
import TecSupermarket.repository.SaleRepository;
import TecSupermarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private UserRepository userRepository;

    // Get user email by jwt
    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String email = auth.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<SaleResponse> getSales() {
        User user = getAuthenticatedUser();
        List<Sale> sales = saleRepository.findSaleByUser(user);
        List<SaleResponse> saleResponsesList = new ArrayList<>();
        SaleResponse saleResponse;
        for (Sale sale : sales) {
            saleResponse = Mapper.toDTO(sale);
            saleResponsesList.add(saleResponse);
        }
        return saleResponsesList;
    }

    @Transactional
    @Override
    public SaleResponse createSale(SaleRequest saleRequest) {
        // Validate
        if (saleRequest == null) throw new RuntimeException("SaleDto is null");
        if (saleRequest.getIdOffice() == null) throw new RuntimeException("Need Office");
        if (saleRequest.getDetails() == null || saleRequest.getDetails().isEmpty()) throw new RuntimeException("Need a product");

        // Search Office
        Office office = officeRepository.findById(saleRequest.getIdOffice()).orElse(null);
        if (office == null) {
            throw new NotFoundException("Office not found");
        }

        User user = getAuthenticatedUser();

        // Create Sale
        Sale sale = new Sale();
        sale.setState(saleRequest.getState());
        sale.setOffice(office);
        sale.setUser(user);
        List<DetailSale> detailSales = new ArrayList<>();

        double totalCalculate = 0.0;

        for(DetailSaleRequest detailSaleRequest  : saleRequest.getDetails()) {
            Product product = productRepository.findByName(detailSaleRequest.getNameProd()).orElseThrow(() ->
                    new RuntimeException("Product don't found" + detailSaleRequest.getNameProd()));
            if (product.getStock() < detailSaleRequest.getStockProd()){
                throw new RuntimeException(
                        "Insufficient stock for product " + product.getName()
                );
            }
            product.setStock(product.getStock() - detailSaleRequest.getStockProd());
            productRepository.save(product);

            DetailSale detailSale = new DetailSale();
            detailSale.setProduct(product);
            detailSale.setPrice(product.getPrice());
            detailSale.setStockProd(detailSaleRequest.getStockProd());
            detailSale.setSale(sale);
            detailSale.setSubtotal(detailSaleRequest.getStockProd()*product.getPrice());
            detailSales.add(detailSale);

            totalCalculate = totalCalculate + (detailSaleRequest.getStockProd()*product.getPrice());
        }

        sale.setDetail(detailSales);
        sale.setTotal(totalCalculate);
        sale = saleRepository.save(sale);
        return Mapper.toDTO(sale);
    }

    @Override
    public SaleResponse updateSale(Long id, SaleRequest saleRequest) {
        //Search Sale
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale == null) throw new RuntimeException("Sale not Found");
        if (saleRequest.getState() != null) {
            sale.setState(saleRequest.getState());
        }
        if (saleRequest.getIdOffice() != null) {
            // Search Office
            Office office = officeRepository.findById(saleRequest.getIdOffice()).orElse(null);
            if (office == null) throw new NotFoundException("Office not found");
            sale.setOffice(office);
        }
        saleRepository.save(sale);
        return Mapper.toDTO(sale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale == null) throw new RuntimeException("Sale not Found");
        saleRepository.delete(sale);
    }
}
