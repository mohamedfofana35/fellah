package lambanghi.fellah.mapper;

import org.mapstruct.*;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RendezVousMapper {
	
    // ---- Entity -> DTO ----
    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "medecin.id", target = "medecinId")
    RendezVousDto toDto(RendezVous entity);

    // ---- DTO -> Entity ----
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "medecinId", target = "medecin.id")
    RendezVous toEntity(RendezVousDto dto);

    // ---- Update partiel ----
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "medecinId", target = "medecin.id")
    void updateRendezVousFromDto(RendezVousDto dto, @MappingTarget RendezVous entity);
}
