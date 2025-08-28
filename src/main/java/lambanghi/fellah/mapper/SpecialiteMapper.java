package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SpecialiteMapper {

	SpecialiteDto toDto(Specialite entity);

	@org.mapstruct.Mapping(target = "medecins", ignore = true)
	Specialite toEntity(SpecialiteDto dto);
	
	@org.mapstruct.Mapping(target = "medecins", ignore = true)
	void updateSpecialiteFromDto(SpecialiteDto dto, @MappingTarget Specialite entity);

}
