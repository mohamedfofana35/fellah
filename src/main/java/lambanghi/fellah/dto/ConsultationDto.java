package lambanghi.fellah.dto;

import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ConsultationDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateConsultation;
    private String compteRendu;
    private Long rendezVousId;
}
