package lambanghi.fellah.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.mapper.impl.PatientMapperImpl;
import lambanghi.fellah.repository.PatientRepository;
import lambanghi.fellah.service.PatientService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    @Override
    public PatientDto create(PatientDto dto) {
        Patient p = PatientMapperImpl.toEntity(dto);

        // Génération numéro dossier fiable : utiliser une séquence en base de préférence.
        String numero = generateNumeroDossier();
        p.setNumeroDossier(numero);

        Patient saved = repository.save(p);
        return PatientMapperImpl.toDto(saved);
    }

    @Override
    public PatientDto findById(Long id) {
        return repository.findById(id).map(PatientMapperImpl::toDto).orElse(null);
    }

    @Override
    public List<PatientDto> findAll() {
        return repository.findAll().stream().map(PatientMapperImpl::toDto).collect(Collectors.toList());
    }

    @Override
    public PatientDto update(Long id, PatientDto dto) {
        Patient existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Patient introuvable"));
        // Mettre à jour champs autorisés
        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setEmail(dto.getEmail());
        existing.setTelephone(dto.getTelephone());
        existing.setDateNaissance(dto.getDateNaissance());
        Patient saved = repository.save(existing);
        return PatientMapperImpl.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private String generateNumeroDossier() {
        // Exemple simple : PAT-<year>-<sequential>
        long seq = repository.count() + 1;
        String year = String.valueOf(LocalDate.now().getYear());
        return String.format("PAT-%s-%05d", year, seq);
    }
}
