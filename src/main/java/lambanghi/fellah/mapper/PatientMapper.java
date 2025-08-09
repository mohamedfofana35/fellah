package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;

public interface PatientMapper {
	 PatientDto toDto(Patient entity);
	 Patient toEntity(PatientDto dto);
}
