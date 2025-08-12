package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lambanghi.fellah.dto.UtilisateurDto;
import lambanghi.fellah.entity.Utilisateur;
@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
	UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

	UtilisateurDto toDto(Utilisateur entity);
	Utilisateur toEntity(UtilisateurDto dto);
}
