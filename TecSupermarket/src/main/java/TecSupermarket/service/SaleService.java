package TecSupermarket.service;

import TecSupermarket.dto.DetailSaleDTO;
import TecSupermarket.dto.SaleDTO;
import TecSupermarket.exception.NotFoundException;
import TecSupermarket.mapper.Mapper;
import TecSupermarket.model.DetailSale;
import TecSupermarket.model.Office;
import TecSupermarket.model.Product;
import TecSupermarket.model.Sale;
import TecSupermarket.repository.OfficeRepository;
import TecSupermarket.repository.ProductRepository;
import TecSupermarket.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleDTO> salesDTO = new ArrayList<>();
        SaleDTO dto;
        for (Sale sale : sales) {
            dto = Mapper.toDTO(sale);
            salesDTO.add(dto);
        }
        return salesDTO;
    }

    @Override
    public SaleDTO createSale(SaleDTO saleDto) {
        // Validate
        if (saleDto == null) throw new RuntimeException("SaleDto is null");
        if (saleDto.getIdOffice() == null) throw new RuntimeException("Need Office");
        if (saleDto.getDetails() == null || saleDto.getDetails().isEmpty()) throw new RuntimeException("Need a product");

        // Search Office
        Office office = officeRepository.findById(saleDto.getIdOffice()).orElse(null);
        if (office == null) {
            throw new NotFoundException("Office not found");
        }

        // Create Sale
        Sale sale = new Sale();
        sale.setDate(saleDto.getDate());
        sale.setState(saleDto.getState());
        sale.setOffice(office);
        sale.setTotal(saleDto.getTotal());

        List<DetailSale> detailSales = new ArrayList<>();

        Double totalCalculate = 0.0;

        for(DetailSaleDTO detailSaleDTO : saleDto.getDetails()) {
            Product product = productRepository.findByName(detailSaleDTO.getNameProd()).orElse(null);
            if (product == null) {
                throw new RuntimeException("Product not Found" + detailSaleDTO.getNameProd());
            }
            DetailSale detailSale = new DetailSale();
            detailSale.setProduct(product);
            detailSale.setPrice(detailSaleDTO.getPrice());
            detailSale.setStockProd(detailSaleDTO.getStockProd());
            detailSale.setSale(sale);

            totalCalculate = totalCalculate + (detailSaleDTO.getPrice()*detailSaleDTO.getStockProd());
        }

        sale.setDetail(detailSales);
        sale = saleRepository.save(sale);

        return Mapper.toDTO(sale);
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDto) {
        //Search Sale
        Sale sale = saleRepository.findById(id).orElse(null);
        if(sale == null) throw new RuntimeException("Sale not Found");
        if (saleDto.getDate() != null) {
            sale.setDate(saleDto.getDate());
        }
        if (saleDto.getDate() != null) {
            sale.setDate(saleDto.getDate());
        }
        if (saleDto.getState() != null) {
            sale.setState(saleDto.getState());
        }
        if (saleDto.getTotal() != null) {
            sale.setTotal(saleDto.getTotal());
        }
        if (saleDto.getIdOffice() != null) {
            // Search Office
            Office office = officeRepository.findById(saleDto.getIdOffice()).orElse(null);
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
