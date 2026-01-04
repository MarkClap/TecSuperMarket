package TecSupermarket.controller;

import TecSupermarket.dto.OfficeDTO;
import TecSupermarket.service.IOfficeService;
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<OfficeDTO>> getOffice(){
        return ResponseEntity.ok(officeService.getOffices());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<OfficeDTO> createOffice(@RequestBody OfficeDTO officeDTO) {
        OfficeDTO create = officeService.createOffice(officeDTO);
        return ResponseEntity.created(URI.create("/api/offices" + create.getId())).body(create);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<OfficeDTO> updateOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO){
        return ResponseEntity.ok(officeService.updateOffice(id, officeDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) {
        officeService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }
}
