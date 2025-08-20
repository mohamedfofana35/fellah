package lambanghi.fellah.service;

import java.util.List;
import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;

public interface ConsultationService {
    Consultation create(Consultation consultation);
    ConsultationDto findById(Long id);
    List<ConsultationDto> findAll();
    ConsultationDto update(Long id, ConsultationDto dto);
    void delete(Long id);
}
