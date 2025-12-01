package TecSupermarket.service;

import TecSupermarket.dto.OfficeDTO;

import java.util.List;

public interface IOfficeService {
    List<OfficeDTO> getOffices();
    OfficeDTO createOffice(OfficeDTO officeDto);
    OfficeDTO updateOffice(Long id, OfficeDTO officeDto);
    Void deleteOffice(Long id);
}
