package TecSupermarket.service;

import TecSupermarket.dto.request.OfficeRequest;
import TecSupermarket.dto.response.OfficeResponse;
import TecSupermarket.exception.NotFoundException;
import TecSupermarket.mapper.Mapper;
import TecSupermarket.model.Office;
import TecSupermarket.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService implements IOfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<OfficeResponse> getOffices() {
        return officeRepository.findAll()
                .stream()
                .map(office -> new OfficeResponse(
                        office.getId(),
                        office.getName(),
                        office.getDirection()
                ))
                .toList();
    }

    public OfficeResponse createOffice(OfficeRequest officeRequest) {
        Office office = Office.builder()
                .name(officeRequest.getName())
                .direction(officeRequest.getDirection())
                .build();
        return Mapper.toDTO(officeRepository.save(office));
    }

    @Override
    public OfficeResponse updateOffice(Long id, OfficeRequest officeRequest) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Office not found"));
        office.setName(officeRequest.getName());
        office.setDirection(officeRequest.getDirection());
        return Mapper.toDTO(officeRepository.save(office));
    }

    @Override
    public void deleteOffice(Long id) {
        if (!officeRepository.existsById(id))
            throw new NotFoundException("Office not found");
        officeRepository.deleteById(id);
    }
}
