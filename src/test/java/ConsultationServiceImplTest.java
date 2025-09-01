import lambanghi.fellah.dto.ConsultationDto;
import lambanghi.fellah.entity.Consultation;
import lambanghi.fellah.entity.RendezVous;
import lambanghi.fellah.mapper.ConsultationMapper;
import lambanghi.fellah.repository.ConsultationRepository;
import lambanghi.fellah.service.impl.ConsultationServiceImpl;

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
class ConsultationServiceImplTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private ConsultationMapper consultationMapper;

    @InjectMocks
    private ConsultationServiceImpl consultationService;

    @Test
    void testCreateConsultation() {
        Consultation consultation = new Consultation(null, LocalDate.of(2024, 9, 1), "Compte rendu", new RendezVous());
        Consultation savedConsultation = new Consultation(1L, LocalDate.of(2024, 9, 1), "Compte rendu", new RendezVous());

        when(consultationRepository.save(consultation)).thenReturn(savedConsultation);

        Consultation result = consultationService.create(consultation);

        assertThat(result.getId()).isEqualTo(1L);
        verify(consultationRepository, times(1)).save(consultation);
    }

    @Test
    void testFindById_Found() {
        Consultation consultation = new Consultation(1L, LocalDate.of(2024, 9, 1), "Compte rendu", new RendezVous(10L, null, null, null, null));
        ConsultationDto dto = new ConsultationDto(LocalDate.of(2024, 9, 1), "Compte rendu", 10L);

        when(consultationRepository.findById(1L)).thenReturn(Optional.of(consultation));
        when(consultationMapper.toDto(consultation)).thenReturn(dto);

        ConsultationDto result = consultationService.findById(1L);

        assertThat(result.getDateConsultation()).isEqualTo(LocalDate.of(2024, 9, 1));
        assertThat(result.getCompteRendu()).isEqualTo("Compte rendu");
        assertThat(result.getRendezVousId()).isEqualTo(10L);
    }

    @Test
    void testFindById_NotFound() {
        when(consultationRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            consultationService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Consultation non trouvée avec id 1");
        }
    }

    @Test
    void testFindAll() {
        Consultation c1 = new Consultation(1L, LocalDate.of(2024, 9, 1), "CR1", new RendezVous());
        Consultation c2 = new Consultation(2L, LocalDate.of(2024, 9, 2), "CR2", new RendezVous());

        ConsultationDto dto1 = new ConsultationDto(LocalDate.of(2024, 9, 1), "CR1", null);
        ConsultationDto dto2 = new ConsultationDto(LocalDate.of(2024, 9, 2), "CR2", null);

        when(consultationRepository.findAll()).thenReturn(java.util.Arrays.asList(c1, c2));
        when(consultationMapper.toDto(c1)).thenReturn(dto1);
        when(consultationMapper.toDto(c2)).thenReturn(dto2);

        var result = consultationService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("compteRendu").containsExactly("CR1", "CR2");
    }

    @Test
    void testUpdateConsultation() {
        Consultation consultation = new Consultation(1L, LocalDate.of(2024, 9, 1), "CR", new RendezVous());
        ConsultationDto dto = new ConsultationDto(LocalDate.of(2024, 9, 2), "CR2",null);
        Consultation updatedConsultation = new Consultation(1L, LocalDate.of(2024, 9, 2), "CR2", new RendezVous());
        ConsultationDto updatedDto = new ConsultationDto(LocalDate.of(2024, 9, 2), "CR2",null);

        when(consultationRepository.findById(1L)).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(updatedConsultation);
        when(consultationMapper.toDto(updatedConsultation)).thenReturn(updatedDto);

        ConsultationDto result = consultationService.update(1L, dto);

        assertThat(result.getDateConsultation()).isEqualTo(LocalDate.of(2024, 9, 2));
        assertThat(result.getCompteRendu()).isEqualTo("CR2");
        verify(consultationRepository, times(1)).save(consultation);
    }

    @Test
    void testUpdateConsultation_NotFound() {
        ConsultationDto dto = new ConsultationDto(LocalDate.of(2024, 9, 2), "CR2",null);
        when(consultationRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            consultationService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Consultation non trouvée avec id 1");
        }
    }

    @Test
    void testDeleteConsultation_Success() {
        when(consultationRepository.existsById(1L)).thenReturn(true);

        consultationService.delete(1L);

        verify(consultationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteConsultation_NotFound() {
        when(consultationRepository.existsById(1L)).thenReturn(false);

        try {
            consultationService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Consultation non trouvée avec id 1");
        }
    }
}
