package lambanghi.fellah.dto;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lambanghi.fellah.enume.Role;



import lombok.*;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString

public class UtilisateurDto {
	  private Long id;

	    private String nom;
	    private String prenom;
	    private String email;
	    private String motDePasse;
	    private String telephone;
	    private LocalDate dateNaissance;
	    @Enumerated(EnumType.STRING)
	    private Role role; // PATIENT, MEDECIN, ADMIN
}
