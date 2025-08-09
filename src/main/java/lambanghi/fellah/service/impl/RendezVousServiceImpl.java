package lambanghi.fellah.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.entity.RendezVous;
import lambanghi.fellah.enume.RdvStatut;
import lambanghi.fellah.mapper.impl.RendezVousMapperImpl;
import lambanghi.fellah.repository.MedecinRepository;
import lambanghi.fellah.repository.PatientRepository;
import lambanghi.fellah.repository.RendezVousRepository;
import lambanghi.fellah.service.RendezVousService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rvRepository;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    @Override
    public RendezVousDto create(RendezVousDto dto) {
        // Validation basique : patient & medecin existent et pas déjà rdv à la même date pour ce medecin
        Patient patient = patientRepository.findById(dto.getPatientId())
            .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        Medecin medecin = medecinRepository.findById(dto.getMedecinId())
            .orElseThrow(() -> new RuntimeException("Medecin non trouvé"));

        LocalDateTime dt = dto.getDateHeure();
        if (rvRepository.existsByMedecinAndDateHeure(medecin, dt)) {
            throw new RuntimeException("Médecin déjà réservé à cette date/heure");
        }

        RendezVous rv = new RendezVous();
        rv.setDateHeure(dt);
        rv.setMedecin(medecin);
        rv.setPatient(patient);
        rv.setStatut(RdvStatut.PRIS);

        RendezVous saved = rvRepository.save(rv);
        return RendezVousMapperImpl.toDto(saved);
    }

    @Override
    public RendezVousDto findById(Long id) {
        return rvRepository.findById(id).map(RendezVousMapperImpl::toDto).orElse(null);
    }

    @Override
    public List<RendezVousDto> findAll() {
        return rvRepository.findAll().stream().map(RendezVousMapperImpl::toDto).collect(Collectors.toList());
    }

    @Override
    public RendezVousDto update(Long id, RendezVousDto dto) {
        RendezVous existing = rvRepository.findById(id).orElseThrow(() -> new RuntimeException("RDV introuvable"));
        existing.setDateHeure(dto.getDateHeure());
        if (dto.getStatut() != null) existing.setStatut(RdvStatut.valueOf(dto.getStatut()));
        RendezVous saved = rvRepository.save(existing);
        return RendezVousMapperImpl.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        rvRepository.deleteById(id);
    }
}
