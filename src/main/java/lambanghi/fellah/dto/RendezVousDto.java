package lambanghi.fellah.dto;

import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class RendezVousDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateHeure;
    private String statut;

    private Long patientId;
    private Long medecinId;
}
