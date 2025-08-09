package lambanghi.fellah.dto;


import lombok.*;

import java.time.LocalDate;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class PatientDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private String numeroDossier;
    private Long adresseId; // lien vers adresse
}
