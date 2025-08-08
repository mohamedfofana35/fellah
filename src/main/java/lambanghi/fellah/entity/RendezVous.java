package lambanghi.fellah.entity;

import jakarta.persistence.*;
import lambanghi.fellah.enume.RdvStatut;


import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor 
@ToString
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    private RdvStatut statut; // PRIS, ANNULE, TERMINE

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_medecin", nullable = false)
    private Medecin medecin;
}
