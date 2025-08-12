package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
@Mapper(componentModel = "spring")
public interface ConsultationMapper {
	ConsultationMapper INSTANCE = Mappers.getMapper(ConsultationMapper.class);

	ConsultationDto toDto(Consultation entity);
	Consultation toEntity(ConsultationDto dto);
}
