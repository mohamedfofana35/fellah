package lambanghi.fellah.controller;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
import lambanghi.fellah.service.SpecialiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialites")
@RequiredArgsConstructor
public class SpecialiteController {

    private final SpecialiteService service;

    @PostMapping
    public ResponseEntity<Specialite> create(@RequestBody Specialite entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialiteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SpecialiteDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialiteDto> update(@PathVariable Long id, @RequestBody SpecialiteDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
