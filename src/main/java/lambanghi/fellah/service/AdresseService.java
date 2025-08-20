package lambanghi.fellah.service;

import java.util.List;
import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;

public interface AdresseService {
    Adresse create(Adresse dto);
    AdresseDto findById(Long id);
    List<AdresseDto> findAll();
    AdresseDto update(Long id, AdresseDto dto);
    void delete(Long id);
}
