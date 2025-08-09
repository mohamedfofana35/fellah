package lambanghi.fellah.mapper;

import lambanghi.fellah.dto.UtilisateurDto;
import lambanghi.fellah.entity.Utilisateur;

public interface UtilisateurMapper {
	UtilisateurDto toDto(Utilisateur entity);
	Utilisateur toEntity(UtilisateurDto dto);
}
