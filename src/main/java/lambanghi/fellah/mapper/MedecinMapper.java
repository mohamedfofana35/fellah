package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedecinMapper {
	@Mapping(target = "specialiteId", source = "specialite.id")
	@Mapping(target = "adresseId", source = "adresse.id")
	MedecinDto toDto(Medecin entity);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "adresse", ignore = true)
	@Mapping(target = "motDePasse", ignore = true)
	@Mapping(target = "rendezVous", ignore = true)
	@Mapping(target = "specialite.id", source = "specialiteId")

	Medecin toEntity(MedecinDto dto);
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "adresse", ignore = true)
	@Mapping(target = "motDePasse", ignore = true)
	@Mapping(target = "rendezVous", ignore = true)
    @Mapping(target = "specialite.id", source = "specialiteId")

	void updateMedecinFromDto(MedecinDto dto, @MappingTarget Medecin entity);

}
