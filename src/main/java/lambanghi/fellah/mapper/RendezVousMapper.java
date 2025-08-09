package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;

public interface RendezVousMapper {
	RendezVousDto toDto(RendezVous entity);
	RendezVous toEntity(RendezVousDto dto);
}
