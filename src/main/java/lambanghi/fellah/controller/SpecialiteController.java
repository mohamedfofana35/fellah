package lambanghi.fellah.controller;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
import lambanghi.fellah.service.SpecialiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/specialites")
@RequiredArgsConstructor
@Tag(name = "Spécialités", description = "Gestion des spécialités médicales")
public class SpecialiteController {

    private final SpecialiteService service;

    @PostMapping
    @Operation(summary = "Créer une spécialité", description = "Ajoute une nouvelle spécialité à la base de données")
    public ResponseEntity<Specialite> create(@RequestBody Specialite entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver une spécialité par ID", description = "Retourne une spécialité en fonction de son identifiant")
    public ResponseEntity<SpecialiteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les spécialités", description = "Retourne la liste complète des spécialités")
    public ResponseEntity<List<SpecialiteDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")

    @Operation(summary = "Mettre à jour une spécialité", description = "Modifie les informations d'une spécialité existante")
    public ResponseEntity<SpecialiteDto> update(@PathVariable Long id, @RequestBody SpecialiteDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une spécialité", description = "Supprime une spécialité de la base de données")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
