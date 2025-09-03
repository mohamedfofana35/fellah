package lambanghi.fellah.controller;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;
import lambanghi.fellah.service.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
@Tag(name = "Rendez-Vous", description = "Gestion des rendez-vous")
public class RendezVousController {

    private final RendezVousService service;

    @PostMapping
    @Operation(summary = "Créer un rendez-vous", description = "Ajoute un nouveau rendez-vous à la base de données")
    public ResponseEntity<RendezVous> create(@RequestBody RendezVous entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un rendez-vous par ID", description = "Retourne un rendez-vous en fonction de son identifiant")
    public ResponseEntity<RendezVousDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les rendez-vous", description = "Retourne la liste complète des rendez-vous")
    public ResponseEntity<List<RendezVousDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un rendez-vous", description = "Modifie les informations d'un rendez-vous existant")
    public ResponseEntity<RendezVousDto> update(@PathVariable Long id, @RequestBody RendezVousDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un rendez-vous", description = "Supprime un rendez-vous de la base de données")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
