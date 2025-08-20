package lambanghi.fellah.service;

import java.util.List;
import lambanghi.fellah.dto.UtilisateurDto;

public interface UtilisateurService {
    UtilisateurDto create(UtilisateurDto dto);
    UtilisateurDto findById(Long id);
    List<UtilisateurDto> findAll();
    UtilisateurDto update(Long id, UtilisateurDto dto);
    void delete(Long id);
}
