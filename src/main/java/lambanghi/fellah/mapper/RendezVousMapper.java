package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;
@Mapper(componentModel = "spring")
public interface RendezVousMapper {
	RendezVousMapper INSTANCE = Mappers.getMapper(RendezVousMapper.class);

	RendezVousDto toDto(RendezVous entity);
	RendezVous toEntity(RendezVousDto dto);
}
