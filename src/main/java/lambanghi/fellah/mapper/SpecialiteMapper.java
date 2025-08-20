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
	Specialite toEntity(SpecialiteDto dto);
    void updateSpecialiteFromDto(SpecialiteDto dto, @MappingTarget Specialite entity);

}
