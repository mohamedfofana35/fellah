package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdresseMapper {

	AdresseDto toDto(Adresse entity);
	Adresse toEntity(AdresseDto dto);
    void updateAdresseFromDto(AdresseDto dto, @MappingTarget Adresse entity);

}
