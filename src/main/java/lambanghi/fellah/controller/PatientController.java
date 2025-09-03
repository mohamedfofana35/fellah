package lambanghi.fellah.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.service.PatientService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Gestion des patients")
public class PatientController {

    private final PatientService service;

    // 📌 Créer un patient
//    @PostMapping
//    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDTO) {
//    	PatientDto savedPatient = service.create(patientDTO);
//        return ResponseEntity.ok(savedPatient);
//    }
    @PostMapping
    @Operation(summary = "Créer un patient", description = "Ajoute un nouveau patient à la base de données")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    	Patient savedPatient = service.create(patient);
        return ResponseEntity.ok(savedPatient);
    }


    // 📌 Récupérer un patient par ID
    @GetMapping("/{id}")
    @Operation(summary = "Trouver un patient par ID", description = "Retourne un patient en fonction de son identifiant")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
    	PatientDto patient = service.findById(id);
        return ResponseEntity.ok(patient);
    }

    // 📌 Récupérer tous les patients
    @GetMapping
    @Operation(summary = "Lister tous les patients", description = "Retourne la liste complète des patients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(service.findAll());
    }

    // 📌 Mettre à jour un patient
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un patient", description = "Modifie les informations d’un patient existant")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDTO) {
    	PatientDto updatedPatient = service.update(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    // 📌 Supprimer un patient
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un patient", description = "Supprime un patient de la base de données")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
