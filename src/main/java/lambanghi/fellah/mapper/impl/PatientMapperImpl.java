package lambanghi.fellah.mapper.impl;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.mapper.PatientMapper;
import lambanghi.fellah.entity.Adresse;

public class PatientMapperImpl implements PatientMapper {

    public PatientDto toDto(Patient p) {
        if (p == null) return null;
        PatientDto dto = new PatientDto();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setPrenom(p.getPrenom());
        dto.setEmail(p.getEmail());
        dto.setTelephone(p.getTelephone());
        dto.setDateNaissance(p.getDateNaissance());
        dto.setNumeroDossier(p.getNumeroDossier());
        if (p.getAdresse() != null) dto.setAdresseId(p.getAdresse().getId());
        return dto;
    }

    public Patient toEntity(PatientDto dto) {
        if (dto == null) return null;
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setEmail(dto.getEmail());
        p.setTelephone(dto.getTelephone());
        p.setDateNaissance(dto.getDateNaissance());
        p.setNumeroDossier(dto.getNumeroDossier());
        if (dto.getAdresseId() != null) {
            Adresse a = new Adresse();
            a.setId(dto.getAdresseId());
            p.setAdresse(a);
        }
        p.setRole(lambanghi.fellah.enume.Role.PATIENT);
        return p;
    }
}
