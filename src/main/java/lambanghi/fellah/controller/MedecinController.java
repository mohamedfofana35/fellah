package lambanghi.fellah.controller;

import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
@Tag(name = "Médecins", description = "Gestion des médecins de la clinique")

public class MedecinController {

    private final MedecinService service;

    @PostMapping
    @Operation(summary = "Créer un médecin", description = "Ajoute un nouveau médecin à la base de données")
    public ResponseEntity<Medecin> create(@RequestBody Medecin entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un médecin par ID", description = "Retourne un médecin en fonction de son identifiant")
    public ResponseEntity<MedecinDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les médecins", description = "Retourne la liste complète des médecins")
    public ResponseEntity<List<MedecinDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un médecin", description = "Modifie les informations d'un médecin existant")
    public ResponseEntity<MedecinDto> update(@PathVariable Long id, @RequestBody MedecinDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un médecin", description = "Supprime un médecin de la base de données")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
