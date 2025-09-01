package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.SpecialiteMapper;
import lambanghi.fellah.repository.SpecialiteRepository;
import lambanghi.fellah.service.SpecialiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialiteServiceImpl implements SpecialiteService {

    private final SpecialiteRepository specialiteRepository;
    private final SpecialiteMapper specialiteMapper;

    @Override
    public Specialite create(Specialite entity) {
        return  specialiteRepository.save(entity);

    }

    @Override
    public SpecialiteDto findById(Long id) {
        Specialite specialite = specialiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité non trouvée avec id " + id));
        return specialiteMapper.toDto(specialite);
    }

    @Override
    public List<SpecialiteDto> findAll() {
        return specialiteRepository.findAll()
                .stream()
                .map(specialiteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialiteDto update(Long id, SpecialiteDto dto) {
        Specialite existing = specialiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité non trouvée avec id " + id));
		specialiteMapper.updateSpecialiteFromDto(dto, existing);
        return specialiteMapper.toDto(specialiteRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!specialiteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Spécialité non trouvée avec id " + id);
        }
        specialiteRepository.deleteById(id);
    }
}
