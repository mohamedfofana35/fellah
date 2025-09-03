package lambanghi.fellah.controller;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
import lambanghi.fellah.service.AdresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/adresses")
@RequiredArgsConstructor
@Tag(name = "Adresses", description = "Gestion des adresses")
public class AdresseController {

    private final AdresseService service;

    @PostMapping
    @Operation(summary = "Créer une adresse", description = "Ajoute une nouvelle adresse à la base de données")
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver une adresse par ID", description = "Retourne une adresse en fonction de son identifiant")
    public ResponseEntity<AdresseDto> getAdresseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les adresses", description = "Retourne la liste complète des adresses")
    public ResponseEntity<List<AdresseDto>> getAllAdresses() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une adresse", description = "Modifie les informations d'une adresse existante")
    public ResponseEntity<AdresseDto> updateAdresse(@PathVariable Long id, @RequestBody AdresseDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une adresse", description = "Supprime une adresse de la base de données")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
