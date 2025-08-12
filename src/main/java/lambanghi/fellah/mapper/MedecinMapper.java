package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
@Mapper(componentModel = "spring")
public interface MedecinMapper {
	MedecinMapper INSTANCE = Mappers.getMapper(MedecinMapper.class);

	MedecinDto toDto(Medecin entity);
	Medecin toEntity(MedecinDto dto);
}
