package TecSupermarket.controller;


import TecSupermarket.dto.request.OfficeRequest;
import TecSupermarket.dto.response.OfficeResponse;
import TecSupermarket.service.IOfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    @Autowired
    IOfficeService officeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OfficeResponse>> getOffice(){
        return ResponseEntity.ok(officeService.getOffices());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<OfficeResponse> createOffice(@Valid @RequestBody OfficeRequest officeRequest) {
        OfficeResponse created = officeService.createOffice(officeRequest);
        return ResponseEntity.created(URI.create("/api/offices" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<OfficeResponse> updateOffice(@PathVariable Long id, @RequestBody OfficeRequest officeRequest){
       return ResponseEntity.ok(officeService.updateOffice(id, officeRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) {
        officeService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }
}
