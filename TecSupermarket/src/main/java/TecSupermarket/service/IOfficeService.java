package TecSupermarket.service;

import TecSupermarket.dto.request.OfficeRequest;
import TecSupermarket.dto.response.OfficeResponse;

import java.util.List;

public interface IOfficeService {
    List<OfficeResponse> getOffices();
    OfficeResponse createOffice(OfficeRequest officeRequest);
    OfficeResponse updateOffice(Long id, OfficeRequest officeRequest);
    void deleteOffice(Long id);
}
