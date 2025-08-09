package lambanghi.fellah.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class RendezVousDto {
    private Long id;
    private LocalDateTime dateHeure;
    private String statut;
    private Long patientId;
    private Long medecinId;
}
