package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.RendezVousMapper;
import lambanghi.fellah.repository.RendezVousRepository;
import lambanghi.fellah.service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;

    @Override
    public RendezVous create(RendezVous entity) {
        return  rendezVousRepository.save(entity);
    }

    @Override
    public RendezVousDto findById(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous non trouvé avec id " + id));
        return rendezVousMapper.toDto(rendezVous);
    }

    @Override
    public List<RendezVousDto> findAll() {
        return rendezVousRepository.findAll()
                .stream()
                .map(rendezVousMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RendezVousDto update(Long id, RendezVousDto dto) {
        RendezVous existing = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rendez-vous non trouvé avec id " + id));
		
        rendezVousMapper.updateRendezVousFromDto(dto, existing);

        return rendezVousMapper.toDto(rendezVousRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!rendezVousRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rendez-vous non trouvé avec id " + id);
        }
        rendezVousRepository.deleteById(id);
    }
}
