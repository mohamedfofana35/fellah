package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;

public interface SpecialiteMapper {
	SpecialiteDto toDto(Specialite entity);
	Specialite toEntity(SpecialiteDto dto);
}
