package lambanghi.fellah.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medecin")
@PrimaryKeyJoinColumn(name = "id_medecin")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString(callSuper = true)
public class Medecin extends Utilisateur {

    @Column(unique = true)
    private String numeroLicence;

    @ManyToOne
    @JoinColumn(name = "id_specialite")
    private Specialite specialite;
}
