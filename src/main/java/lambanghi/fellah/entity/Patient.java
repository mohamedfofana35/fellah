package lambanghi.fellah.entity;

//import java.time.LocalDate;

import jakarta.persistence.Entity;
//import lambanghi.fellah.enume.Role;
//import lambanghi.fellah.entity.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Patient  extends Utilisateur{
	
    private String numeroDossier;
    
//	public Patient(long id, String nom, String prenom, String email, String motDePasse, String telephone, LocalDate dateNaissance,Role role,Adresse adresse,String numeroDossier) {
//			super(id, nom, prenom, email, motDePasse, telephone, dateNaissance, role, adresse)
//			this.setNumeroDossier(numeroDossier);
//			
//	}
    
}
