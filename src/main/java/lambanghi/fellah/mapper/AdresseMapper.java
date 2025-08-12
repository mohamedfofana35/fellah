package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
@Mapper(componentModel = "spring")
public interface AdresseMapper {
	AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);

	AdresseDto toDto(Adresse entity);
	Adresse toEntity(AdresseDto dto);
}
