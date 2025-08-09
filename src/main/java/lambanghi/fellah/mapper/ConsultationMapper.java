package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;

public interface ConsultationMapper {
	ConsultationDto toDto(Consultation entity);
	Consultation toEntity(ConsultationDto dto);
}
