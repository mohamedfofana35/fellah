package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {

	 @Mapping(target = "adresseId", source = "adresse.id")
	 PatientDto toDto(Patient entity);
	
	 @Mapping(target = "adresse.id", source = "adresseId")
	 @Mapping(target = "id", ignore = true)
	 @Mapping(target = "motDePasse", ignore = true)
	 @Mapping(target = "rendezVous", ignore = true)
	 Patient toEntity(PatientDto dto) ;
	
	 @Mapping(target = "adresse.id", source = "adresseId")
	 @Mapping(target = "id", ignore = true)
	 @Mapping(target = "motDePasse", ignore = true)
	 @Mapping(target = "rendezVous", ignore = true)
	void updatePatientFromDto(PatientDto dto, @MappingTarget Patient entity);
}
