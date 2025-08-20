package lambanghi.fellah.entity;

import jakarta.persistence.*;
import lambanghi.fellah.enume.RdvStatut;


import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateHeure;

    @Enumerated(EnumType.STRING)
    private RdvStatut statut; // PRIS, ANNULE, TERMINE

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecinId", nullable = false)
    private Medecin medecin;
}
