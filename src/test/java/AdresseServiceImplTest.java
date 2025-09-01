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

    @Test
    void testFindAll() {
        Adresse adresse1 = new Adresse(1L, "Q1", "C1", "V1", null);
        Adresse adresse2 = new Adresse(2L, "Q2", "C2", "V2", null);
        AdresseDto dto1 = new AdresseDto("Q1", "C1", "V1");
        AdresseDto dto2 = new AdresseDto("Q2", "C2", "V2");

        when(adresseRepository.findAll()).thenReturn(java.util.Arrays.asList(adresse1, adresse2));
        when(adresseMapper.toDto(adresse1)).thenReturn(dto1);
        when(adresseMapper.toDto(adresse2)).thenReturn(dto2);

        var result = adresseService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("quartier").containsExactly("Q1", "Q2");
    }

    @Test
    void testUpdateAdresse() {
        Adresse adresse = new Adresse(1L, "Q", "C", "V", null);
        AdresseDto dto = new AdresseDto("Q2", "C2", "V2");
        Adresse updatedAdresse = new Adresse(1L, "Q2", "C2", "V2", null);
        AdresseDto updatedDto = new AdresseDto("Q2", "C2", "V2");

        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));
        // Simulate mapper updating the entity
        // No need to stub updateAdresseFromDto, it's void
        when(adresseRepository.save(adresse)).thenReturn(updatedAdresse);
        when(adresseMapper.toDto(updatedAdresse)).thenReturn(updatedDto);

        AdresseDto result = adresseService.update(1L, dto);

        assertThat(result.getQuartier()).isEqualTo("Q2");
        assertThat(result.getCommune()).isEqualTo("C2");
        assertThat(result.getVille()).isEqualTo("V2");
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    void testUpdateAdresse_NotFound() {
        AdresseDto dto = new AdresseDto("Q2", "C2", "V2");
        when(adresseRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            adresseService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Adresse non trouvée avec id 1");
        }
    }

    @Test
    void testDeleteAdresse_Success() {
        when(adresseRepository.existsById(1L)).thenReturn(true);

        adresseService.delete(1L);

        verify(adresseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAdresse_NotFound() {
        when(adresseRepository.existsById(1L)).thenReturn(false);

        try {
            adresseService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Adresse non trouvée avec id 1");
        }
    }
}
