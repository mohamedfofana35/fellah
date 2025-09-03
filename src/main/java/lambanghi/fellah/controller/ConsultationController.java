package lambanghi.fellah.controller;

import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
import lambanghi.fellah.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
@Tag(name = "Consultations", description = "Gestion des consultations médicales")
public class ConsultationController {

    private final ConsultationService service;

    @PostMapping
    @Operation(summary = "Créer une consultation", description = "Ajoute une nouvelle consultation à la base de données")
    public ResponseEntity<Consultation> create(@RequestBody Consultation entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver une consultation par ID", description = "Retourne une consultation en fonction de son identifiant")
    public ResponseEntity<ConsultationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les consultations", description = "Retourne la liste complète des consultations")
    public ResponseEntity<List<ConsultationDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une consultation", description = "Modifie les informations d’une consultation existante")
    public ResponseEntity<ConsultationDto> update(@PathVariable Long id, @RequestBody ConsultationDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une consultation", description = "Supprime une consultation de la base de données")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
