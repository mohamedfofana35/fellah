package lambanghi.fellah.service;

import java.util.List;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;

public interface PatientService {
//	PatientDto create(PatientDto dto);
	public Patient create(Patient patient);
	PatientDto findById(Long id);
    List<PatientDto> findAll();
    PatientDto update(Long id, PatientDto dto);
    void delete(Long id);
}
