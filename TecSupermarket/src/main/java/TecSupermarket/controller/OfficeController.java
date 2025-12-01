package TecSupermarket.controller;

import TecSupermarket.dto.OfficeDTO;
import TecSupermarket.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    @Autowired
    IOfficeService officeService;

    @GetMapping
    public ResponseEntity<List<OfficeDTO>> getOffice(){
        return ResponseEntity.ok(officeService.getOffices());
    }

    @PostMapping
    public ResponseEntity<OfficeDTO> createOffice(@RequestBody OfficeDTO officeDTO) {
        OfficeDTO create = officeService.createOffice(officeDTO);
        return ResponseEntity.created(URI.create("/api/offices" + create.getId())).body(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfficeDTO> updateOffice(@PathVariable Long id, @RequestBody OfficeDTO officeDTO){
        return ResponseEntity.ok(officeService.updateOffice(id, officeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable Long id) {
        officeService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }
}
