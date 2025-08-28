//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import lambanghi.fellah.dto.AdresseDto;
import lambanghi.fellah.entity.Adresse;
import lambanghi.fellah.mapper.AdresseMapper;
import lambanghi.fellah.repository.AdresseRepository;
//import lambanghi.fellah.service.AdresseService;
import lambanghi.fellah.service.impl.AdresseServiceImpl;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


class AdresseServiceImplTest {

    @Mock
    private AdresseRepository adresseRepository;

    @Mock
    private AdresseMapper adresseMapper;

    @InjectMocks
    private AdresseServiceImpl adresseService;

    @Test
    void testCreateAdresse() {
        Adresse adresse = new Adresse(null, "Quartier1", "Commune1", "Ville1", null);
        Adresse savedAdresse = new Adresse(1L, "Quartier1", "Commune1", "Ville1", null);

        when(adresseRepository.save(adresse)).thenReturn(savedAdresse);

        Adresse result = adresseService.create(adresse);

        assertThat(result.getId()).isEqualTo(1L);
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    void testFindById_Found() {
        Adresse adresse = new Adresse(1L, "Q", "C", "V", null);
        AdresseDto dto = new AdresseDto("Q", "C", "V");

        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));
        when(adresseMapper.toDto(adresse)).thenReturn(dto);

        AdresseDto result = adresseService.findById(1L);

        assertThat(result.getQuartier()).isEqualTo("Q");
        assertThat(result.getVille()).isEqualTo("V");
    }

    @Test
    void testFindById_NotFound() {
        when(adresseRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            adresseService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Adresse non trouvée avec id 1");
        }
    }
}
