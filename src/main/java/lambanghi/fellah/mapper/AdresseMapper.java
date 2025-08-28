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

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "utilisateurs", ignore = true)
    Adresse toEntity(AdresseDto dto);

    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "utilisateurs", ignore = true)
    void updateAdresseFromDto(AdresseDto dto, @MappingTarget Adresse entity);
}