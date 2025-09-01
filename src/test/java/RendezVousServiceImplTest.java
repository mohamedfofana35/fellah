import lambanghi.fellah.dto.RendezVousDto;
import lambanghi.fellah.entity.RendezVous;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.enume.RdvStatut;
import lambanghi.fellah.mapper.RendezVousMapper;
import lambanghi.fellah.repository.RendezVousRepository;
import lambanghi.fellah.service.impl.RendezVousServiceImpl;

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
class RendezVousServiceImplTest {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @Mock
    private RendezVousMapper rendezVousMapper;

    @InjectMocks
    private RendezVousServiceImpl rendezVousService;

    @Test
    void testCreateRendezVous() {
        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous rendezVous = new RendezVous(null, LocalDate.of(2024, 9, 1), RdvStatut.PRIS, patient, medecin);
        RendezVous savedRendezVous = new RendezVous(1L, LocalDate.of(2024, 9, 1), RdvStatut.PRIS, patient, medecin);

        when(rendezVousRepository.save(rendezVous)).thenReturn(savedRendezVous);

        RendezVous result = rendezVousService.create(rendezVous);

        assertThat(result.getId()).isEqualTo(1L);
        verify(rendezVousRepository, times(1)).save(rendezVous);
    }

    @Test
    void testFindById_Found() {
        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous rendezVous = new RendezVous(1L, LocalDate.of(2024, 9, 1), RdvStatut.PRIS, patient, medecin);
        RendezVousDto dto = new RendezVousDto(1L, LocalDate.of(2024, 9, 1), "PRIS", 2L, 3L);

        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rendezVous));
        when(rendezVousMapper.toDto(rendezVous)).thenReturn(dto);

        RendezVousDto result = rendezVousService.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatut()).isEqualTo("PRIS");
        assertThat(result.getDateHeure()).isEqualTo(LocalDate.of(2024, 9, 1));
    }

    @Test
    void testFindById_NotFound() {
        when(rendezVousRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            rendezVousService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Rendez-vous non trouvé avec id 1");
        }
    }

    @Test
    void testFindAll() {
        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous r1 = new RendezVous(1L, LocalDate.of(2024, 9, 1), RdvStatut.PRIS, patient, medecin);
        RendezVous r2 = new RendezVous(2L, LocalDate.of(2024, 9, 2), RdvStatut.ANNULE, patient, medecin);
        RendezVousDto dto1 = new RendezVousDto(1L, LocalDate.of(2024, 9, 1), "PRIS", 2L, 3L);
        RendezVousDto dto2 = new RendezVousDto(2L, LocalDate.of(2024, 9, 2), "ANNULE", 2L, 3L);

        when(rendezVousRepository.findAll()).thenReturn(java.util.Arrays.asList(r1, r2));
        when(rendezVousMapper.toDto(r1)).thenReturn(dto1);
        when(rendezVousMapper.toDto(r2)).thenReturn(dto2);

        var result = rendezVousService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("statut").containsExactly("PRIS", "ANNULE");
    }

    @Test
    void testUpdateRendezVous() {
        Patient patient = new Patient();
        Medecin medecin = new Medecin();
        RendezVous rendezVous = new RendezVous(1L, LocalDate.of(2024, 9, 1), RdvStatut.PRIS, patient, medecin);
        RendezVousDto dto = new RendezVousDto(1L, LocalDate.of(2024, 9, 2), "TERMINE", 2L, 3L);
        RendezVous updatedRendezVous = new RendezVous(1L, LocalDate.of(2024, 9, 2), RdvStatut.TERMINE, patient, medecin);
        RendezVousDto updatedDto = new RendezVousDto(1L, LocalDate.of(2024, 9, 2), "TERMINE", 2L, 3L);

        when(rendezVousRepository.findById(1L)).thenReturn(Optional.of(rendezVous));
        when(rendezVousRepository.save(rendezVous)).thenReturn(updatedRendezVous);
        when(rendezVousMapper.toDto(updatedRendezVous)).thenReturn(updatedDto);

        RendezVousDto result = rendezVousService.update(1L, dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStatut()).isEqualTo("TERMINE");
        assertThat(result.getDateHeure()).isEqualTo(LocalDate.of(2024, 9, 2));
        verify(rendezVousRepository, times(1)).save(rendezVous);
    }

    @Test
    void testUpdateRendezVous_NotFound() {
        RendezVousDto dto = new RendezVousDto(1L, LocalDate.of(2024, 9, 2), "TERMINE", 2L, 3L);
        when(rendezVousRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            rendezVousService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Rendez-vous non trouvé avec id 1");
        }
    }

    @Test
    void testDeleteRendezVous_Success() {
        when(rendezVousRepository.existsById(1L)).thenReturn(true);

        rendezVousService.delete(1L);

        verify(rendezVousRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteRendezVous_NotFound() {
        when(rendezVousRepository.existsById(1L)).thenReturn(false);

        try {
            rendezVousService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Rendez-vous non trouvé avec id 1");
        }
    }
}
