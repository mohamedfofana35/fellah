import lambanghi.fellah.dto.MedecinDto;
import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.mapper.MedecinMapper;
import lambanghi.fellah.repository.MedecinRepository;
import lambanghi.fellah.service.impl.MedecinServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedecinServiceImplTest {

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private MedecinMapper medecinMapper;

    @InjectMocks
    private MedecinServiceImpl medecinService;

    @Test
    void testCreateMedecin() {
        Medecin medecin = new Medecin();
        medecin.setNumeroLicence("LIC123");
        Medecin savedMedecin = new Medecin();
        savedMedecin.setNumeroLicence("LIC123");
        when(medecinRepository.save(medecin)).thenReturn(savedMedecin);

        Medecin result = medecinService.create(medecin);

        assertThat(result.getNumeroLicence()).isEqualTo("LIC123");
        verify(medecinRepository, times(1)).save(medecin);
    }

    @Test
    void testFindById_Found() {
        Medecin medecin = new Medecin( "LIC123", null, null);
        MedecinDto dto = new MedecinDto(1L, "Nom", "Prenom", "email@test.com", "0600000000", LocalDate.of(1980, 1, 1), "LIC123", 2L, 3L);

        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));
        when(medecinMapper.toDto(medecin)).thenReturn(dto);

        MedecinDto result = medecinService.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Nom");
        assertThat(result.getNumeroLicence()).isEqualTo("LIC123");
    }

    @Test
    void testFindById_NotFound() {
        when(medecinRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            medecinService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Médecin non trouvé avec id 1");
        }
    }
    @Test
    void testFindAll() {
        Medecin m1 = new Medecin();
        Medecin m2 = new Medecin( "LIC002", null, null);
        MedecinDto dto1 = new MedecinDto(1L, "Nom1", "Prenom1", "email1@test.com", "0600000001", LocalDate.of(1980, 1, 1), "LIC001", 2L, 3L);
        MedecinDto dto2 = new MedecinDto(2L, "Nom2", "Prenom2", "email2@test.com", "0600000002", LocalDate.of(1985, 2, 2), "LIC002", 2L, 3L);

        when(medecinRepository.findAll()).thenReturn(java.util.Arrays.asList(m1, m2));
        when(medecinMapper.toDto(m1)).thenReturn(dto1);
        when(medecinMapper.toDto(m2)).thenReturn(dto2);

        var result = medecinService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("nom").containsExactly("Nom1", "Nom2");
    }

    @Test
    void testUpdateMedecin() {
        Medecin medecin = new Medecin( "LIC123", null, null);
        MedecinDto dto = new MedecinDto(1L, "NomModif", "PrenomModif", "email@test.com", "0600000000", LocalDate.of(1980, 1, 1), "LIC123", 2L, 3L);
        Medecin updatedMedecin = new Medecin("LIC123", null, null);
        MedecinDto updatedDto = new MedecinDto(1L, "NomModif", "PrenomModif", "email@test.com", "0600000000", LocalDate.of(1980, 1, 1), "LIC123", 2L, 3L);

        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));
        when(medecinRepository.save(medecin)).thenReturn(updatedMedecin);
        when(medecinMapper.toDto(updatedMedecin)).thenReturn(updatedDto);

        MedecinDto result = medecinService.update(1L, dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("NomModif");
        verify(medecinRepository, times(1)).save(medecin);
    }

    @Test
    void testUpdateMedecin_NotFound() {
        MedecinDto dto = new MedecinDto(1L, "NomModif", "PrenomModif", "email@test.com", "0600000000", LocalDate.of(1980, 1, 1), "LIC123", 2L, 3L);
        when(medecinRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            medecinService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Médecin non trouvé avec id 1");
        }
    }

    @Test
    void testDeleteMedecin_Success() {
        when(medecinRepository.existsById(1L)).thenReturn(true);

        medecinService.delete(1L);

        verify(medecinRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteMedecin_NotFound() {
        when(medecinRepository.existsById(1L)).thenReturn(false);

        try {
            medecinService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Médecin non trouvé avec id 1");
        }
    }
}