package lambanghi.fellah.service.patient;

import java.util.List;

import lambanghi.fellah.dto.PatientDto;

public interface PatientService {
	PatientDto create(PatientDto dto);
	PatientDto findById(Long id);
    List<PatientDto> findAll();
    PatientDto update(Long id, PatientDto dto);
    void delete(Long id);
}
