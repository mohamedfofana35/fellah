package lambanghi.fellah.service;

import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;

import java.util.List;

public interface RendezVousService {
    RendezVous create(RendezVous entity);
    RendezVousDto findById(Long id);
    List<RendezVousDto> findAll();
    RendezVousDto update(Long id, RendezVousDto dto);
    void delete(Long id);
}
