package lambanghi.fellah.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lambanghi.fellah.enume.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // ou SINGLE_TABLE, selon ton choix
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Utilisateur {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private LocalDate dateNaissance;
    @Enumerated(EnumType.STRING)
    private Role role; // PATIENT, MEDECIN, ADMIN
    
    @ManyToOne
    @JoinColumn(name = "adresse")
    private Adresse adresse;
}