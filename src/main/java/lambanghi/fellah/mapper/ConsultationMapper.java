package lambanghi.fellah.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
@Mapper(componentModel = "spring",
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultationMapper {
	// ---- Entity -> DTO ----
	@Mapping(source = "rendezVous.id", target = "rendezVousId")
	ConsultationDto toDto(Consultation entity);

	// ---- DTO -> Entity ----
	@Mapping(source = "rendezVousId", target = "rendezVous.id")
	@Mapping(target = "id", ignore = true)
	Consultation toEntity(ConsultationDto dto);

	// ---- Update partiel ----
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(source = "rendezVousId", target = "rendezVous.id")
	@Mapping(target = "id", ignore = true)
	void updateConsultationFromDto(ConsultationDto dto, @MappingTarget Consultation entity);
}
