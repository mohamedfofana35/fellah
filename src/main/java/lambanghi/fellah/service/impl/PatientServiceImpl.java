package lambanghi.fellah.service.impl;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.exception.ResourceNotFoundException;
import lambanghi.fellah.mapper.PatientMapper;
import lambanghi.fellah.repository.PatientRepository;
import lambanghi.fellah.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
//	@Override
//	public PatientDto create(PatientDto dto) {
//        Patient entity = patientMapper.toEntity(dto);
//        Patient saved = patientRepository.save(entity);
//		return patientMapper.toDto(saved);
//	}
	@Override
	public Patient create(Patient patient) {
        return patientRepository.save(patient);
	}
	@Override
	public PatientDto findById(Long id) {
		Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec id " + id));
        return patientMapper.toDto(patient);
	}
	@Override
	public List<PatientDto> findAll() {
		 return patientRepository.findAll()
	                .stream()
	                .map(patientMapper::toDto)
	                .collect(Collectors.toList());
	}
	@Override
	public PatientDto update(Long id, PatientDto dto) {
		 Patient existing = patientRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé avec id " + id));
		// MapStruct va ignorer les champs nuls
		 patientMapper.updatePatientFromDto(dto, existing);
	        return patientMapper.toDto(patientRepository.save(existing));
	}
	@Override
	public void delete(Long id) {
		 if (!patientRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Patient non trouvé avec id " + id);
	        }
	        patientRepository.deleteById(id);
		
	}

   
}
