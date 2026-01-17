package TecSupermarket.controller;

import TecSupermarket.dto.request.SaleRequest;
import TecSupermarket.dto.response.SaleResponse;
import TecSupermarket.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    ISaleService saleService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<SaleResponse>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest saleRequest){
        SaleResponse created = saleService.createSale(saleRequest);
        return ResponseEntity.created(URI.create("/api/sales" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long id, @RequestBody SaleRequest saleRequest) {
        return ResponseEntity.ok(saleService.updateSale(id, saleRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
