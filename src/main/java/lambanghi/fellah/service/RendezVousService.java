package lambanghi.fellah.service;

import lambanghi.fellah.dto.RendezVousDto;
import java.util.List;

public interface RendezVousService {
    RendezVousDto create(RendezVousDto dto);
    RendezVousDto findById(Long id);
    List<RendezVousDto> findAll();
    RendezVousDto update(Long id, RendezVousDto dto);
    void delete(Long id);
}
