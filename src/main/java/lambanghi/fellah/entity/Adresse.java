package lambanghi.fellah.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "adresse")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quartier;
    private String commune;
    private String ville;
    
    @OneToMany(mappedBy = "adresse")
    private List<Utilisateur> utilisateurs;
}
