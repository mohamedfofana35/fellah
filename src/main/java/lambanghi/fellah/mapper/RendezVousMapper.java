package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;

public class RendezVousMapper {

    public static RendezVousDto toDto(RendezVous r) {
        if (r == null) return null;
        RendezVousDto dto = new RendezVousDto();
        dto.setId(r.getId());
        dto.setDateHeure(r.getDateHeure());
        dto.setStatut(r.getStatut().name());
        if (r.getPatient() != null) dto.setPatientId(r.getPatient().getId());
        if (r.getMedecin() != null) dto.setMedecinId(r.getMedecin().getId());
        return dto;
    }

}
