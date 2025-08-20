package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.MedecinMapper;
import lambanghi.fellah.repository.MedecinRepository;
import lambanghi.fellah.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    @Override
    public Medecin create(Medecin entity) {
        return medecinRepository.save(entity);
    }

    @Override
    public MedecinDto findById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médecin non trouvé avec id " + id));
        return medecinMapper.toDto(medecin);
    }

    @Override
    public List<MedecinDto> findAll() {
        return medecinRepository.findAll()
                .stream()
                .map(medecinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedecinDto update(Long id, MedecinDto dto) {
        Medecin existing = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médecin non trouvé avec id " + id));
		 medecinMapper.updateMedecinFromDto(dto, existing);

        return medecinMapper.toDto(medecinRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!medecinRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médecin non trouvé avec id " + id);
        }
        medecinRepository.deleteById(id);
    }
}
