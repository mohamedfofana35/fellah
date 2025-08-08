package lambanghi.fellah.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "specialite")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Specialite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    @OneToMany(mappedBy = "specialite")
    private List<Medecin> medecins;
}
