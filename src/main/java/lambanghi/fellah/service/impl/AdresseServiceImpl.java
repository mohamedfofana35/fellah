package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.AdresseMapper;
import lambanghi.fellah.repository.AdresseRepository;
import lambanghi.fellah.service.AdresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRepository;
    private final AdresseMapper adresseMapper;

    @Override
    public Adresse create(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @Override
    public AdresseDto findById(Long id) {
        Adresse adresse = adresseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse non trouvée avec id " + id));
        return adresseMapper.toDto(adresse);
    }

    @Override
    public List<AdresseDto> findAll() {
        return adresseRepository.findAll()
                .stream()
                .map(adresseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdresseDto update(Long id, AdresseDto dto) {
        Adresse existing = adresseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse non trouvée avec id " + id));

        adresseMapper.updateAdresseFromDto(dto, existing);

        return adresseMapper.toDto(adresseRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!adresseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adresse non trouvée avec id " + id);
        }
        adresseRepository.deleteById(id);
    }
}
