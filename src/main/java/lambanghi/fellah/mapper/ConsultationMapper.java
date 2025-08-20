package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConsultationMapper {

	ConsultationDto toDto(Consultation entity);
	Consultation toEntity(ConsultationDto dto);
    void updateConsultationFromDto(ConsultationDto dto, @MappingTarget Consultation entity);

}
