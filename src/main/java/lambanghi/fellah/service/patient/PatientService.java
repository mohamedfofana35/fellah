package lambanghi.fellah.service.patient;

import java.util.List;

import lambanghi.fellah.entity.Patient;

public interface PatientService {
	Patient create(Patient patient);
	Patient findById(Long id);
    List<Patient> findAll();
    Patient update(Long id, Patient patient);
    void delete(Long id);
}
