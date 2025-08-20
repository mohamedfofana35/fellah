package lambanghi.fellah.dto;

import java.time.LocalDate;




import lombok.*;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString

public class UtilisateurDto {

	    private String nom;
	    private String prenom;
	    private String email;
	    private String motDePasse;
	    private String telephone;
	    private LocalDate dateNaissance;
	   
}
