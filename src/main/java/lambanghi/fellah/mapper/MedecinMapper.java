package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;

public interface MedecinMapper {
	MedecinDto toDto(Medecin entity);
	Medecin toEntity(MedecinDto dto);
}
