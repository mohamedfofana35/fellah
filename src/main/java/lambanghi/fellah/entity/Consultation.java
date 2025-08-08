package lambanghi.fellah.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultation")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateConsultation;

    @Column(columnDefinition = "TEXT")
    private String compteRendu;

    @OneToOne
    @JoinColumn(name = "id_rdv", unique = true)
    private RendezVous rendezVous;
}
