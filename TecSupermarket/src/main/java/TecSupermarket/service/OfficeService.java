package TecSupermarket.service;

import TecSupermarket.dto.OfficeDTO;
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
    public List<OfficeDTO> getOffices() {
        return officeRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public OfficeDTO createOffice(OfficeDTO officeDto) {
        Office office = Office.builder()
                .name(officeDto.getName())
                .direction(officeDto.getDirection())
                .build();
        return Mapper.toDTO(officeRepository.save(office));
    }

    @Override
    public OfficeDTO updateOffice(Long id, OfficeDTO officeDto) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Office not found"));
        office.setName(officeDto.getName());
        office.setDirection(officeDto.getDirection());
        return Mapper.toDTO(officeRepository.save(office));
    }

    @Override
    public void deleteOffice(Long id) {
        if (!officeRepository.existsById(id))
            throw new NotFoundException("Office not found");
        officeRepository.deleteById(id);
    }
}
