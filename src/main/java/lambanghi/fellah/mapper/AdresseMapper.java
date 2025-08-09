package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;

public interface AdresseMapper {
	AdresseDto toDto(Adresse entity);
	Adresse toEntity(AdresseDto dto);
}
