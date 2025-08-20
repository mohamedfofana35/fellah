package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedecinMapper {

	MedecinDto toDto(Medecin entity);
	Medecin toEntity(MedecinDto dto);
    void updateMedecinFromDto(MedecinDto dto, @MappingTarget Medecin entity);

}
