package lambanghi.fellah.service;

import java.util.List;
import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;

public interface SpecialiteService {
    Specialite create(Specialite entity);
    SpecialiteDto findById(Long id);
    List<SpecialiteDto> findAll();
    SpecialiteDto update(Long id, SpecialiteDto dto);
    void delete(Long id);
}
