package lambanghi.fellah.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateConsultation;

    @Column(columnDefinition = "TEXT")
    private String compteRendu;

    @OneToOne //(mappedBy = "id_rdv", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rdv", unique = true)
    private RendezVous rendezVous;
}
