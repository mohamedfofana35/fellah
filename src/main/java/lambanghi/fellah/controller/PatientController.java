package lambanghi.fellah.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.service.PatientService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    // 📌 Créer un patient
//    @PostMapping
//    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDTO) {
//    	PatientDto savedPatient = service.create(patientDTO);
//        return ResponseEntity.ok(savedPatient);
//    }
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    	Patient savedPatient = service.create(patient);
        return ResponseEntity.ok(savedPatient);
    }


    // 📌 Récupérer un patient par ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
    	PatientDto patient = service.findById(id);
        return ResponseEntity.ok(patient);
    }

    // 📌 Récupérer tous les patients
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(service.findAll());
    }

    // 📌 Mettre à jour un patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDTO) {
    	PatientDto updatedPatient = service.update(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    // 📌 Supprimer un patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
