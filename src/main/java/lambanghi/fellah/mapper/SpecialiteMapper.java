package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
@Mapper(componentModel = "spring")
public interface SpecialiteMapper {
	SpecialiteMapper INSTANCE = Mappers.getMapper(SpecialiteMapper.class);

	SpecialiteDto toDto(Specialite entity);
	Specialite toEntity(SpecialiteDto dto);
}
