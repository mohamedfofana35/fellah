package lambanghi.fellah.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AdresseDto {
    private Long id;
    private String quartier;
    private String commune;
    private String ville;
    private String complement;
    
    
}
