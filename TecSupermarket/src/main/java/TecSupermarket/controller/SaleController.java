package TecSupermarket.controller;

import TecSupermarket.dto.SaleDTO;
import TecSupermarket.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO){
        SaleDTO create = saleService.createSale(saleDTO);
        return ResponseEntity.created(URI.create("/api/sales" + create.getId())).body(create);
    }

    @PutMapping("/{id}")
    public SaleDTO updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDto) {
        return saleService.updateSale(id,saleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
