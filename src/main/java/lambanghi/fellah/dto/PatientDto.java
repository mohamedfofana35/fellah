package lambanghi.fellah.dto;


import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class PatientDto {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private String numeroDossier;
    private Long adresseId; // lien vers adresse
}
