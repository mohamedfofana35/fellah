package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.ConsultationMapper;
import lambanghi.fellah.repository.ConsultationRepository;
import lambanghi.fellah.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    @Override
    public Consultation create(Consultation entity) {
        return consultationRepository.save(entity);
    }

    @Override
    public ConsultationDto findById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation non trouvée avec id " + id));
        return consultationMapper.toDto(consultation);
    }

    @Override
    public List<ConsultationDto> findAll() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationDto update(Long id, ConsultationDto dto) {
        Consultation existing = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation non trouvée avec id " + id));
		
        consultationMapper.updateConsultationFromDto(dto, existing);

        return consultationMapper.toDto(consultationRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consultation non trouvée avec id " + id);
        }
        consultationRepository.deleteById(id);
    }
}
