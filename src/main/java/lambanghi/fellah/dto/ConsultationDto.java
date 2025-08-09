package lambanghi.fellah.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ConsultationDto {
    private Long id;
    private LocalDateTime dateConsultation;
    private String compteRendu;
    private Long rendezVousId;
}
