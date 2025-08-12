package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
@Mapper(componentModel = "spring")
public interface PatientMapper {
	PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

	 PatientDto toDto(Patient entity);
	 Patient toEntity(PatientDto dto) ;
}
