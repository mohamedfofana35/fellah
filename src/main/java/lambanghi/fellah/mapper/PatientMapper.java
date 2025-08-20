package lambanghi.fellah.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Adresse;
import lambanghi.fellah.entity.Patient;
@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {

	 PatientDto toDto(Patient entity);
	
	 @Mapping(target = "adresse", expression = "java(mapAdresse(dto.getAdresseId()))")
	 Patient toEntity(PatientDto dto) ;
	 default Adresse mapAdresse(Long adresseId) {
	        if (adresseId == null) {
	            return null;
	        }
	        Adresse adresse = new Adresse();
	        adresse.setId(adresseId);
	        return adresse;
	 }
		// ⬇️ Ici on met à jour seulement les champs non-nuls du DTO
	    void updatePatientFromDto(PatientDto dto, @MappingTarget Patient entity);
}
