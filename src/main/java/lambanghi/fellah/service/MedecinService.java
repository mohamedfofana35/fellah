package lambanghi.fellah.service;

import java.util.List;
import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;

public interface MedecinService {
    Medecin create(Medecin entity);
    MedecinDto findById(Long id);
    List<MedecinDto> findAll();
    MedecinDto update(Long id, MedecinDto dto);
    void delete(Long id);
}
