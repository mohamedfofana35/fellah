package lambanghi.fellah.controller;

import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
import lambanghi.fellah.service.AdresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adresses")
@RequiredArgsConstructor
public class AdresseController {

    private final AdresseService service;

    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseDto> getAdresseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdresseDto>> getAllAdresses() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresseDto> updateAdresse(@PathVariable Long id, @RequestBody AdresseDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
