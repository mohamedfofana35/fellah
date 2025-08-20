package lambanghi.fellah.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

//import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
//import lambanghi.fellah.enume.Role;
//import lambanghi.fellah.entity.Utilisateur;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Patient  extends Utilisateur{
    @Column(unique = true)
    private String numeroDossier;
  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  private List<RendezVous> rendezVous;

  
}
