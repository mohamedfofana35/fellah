
import lambanghi.fellah.dto.PatientDto;
import lambanghi.fellah.entity.Patient;
import lambanghi.fellah.mapper.PatientMapper;
import lambanghi.fellah.repository.PatientRepository;
import lambanghi.fellah.service.impl.PatientServiceImpl;

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
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        patient.setNumeroDossier("DOS123");

        Patient savedPatient = new Patient();
        savedPatient.setNumeroDossier("DOS123");

        when(patientRepository.save(patient)).thenReturn(savedPatient);

        Patient result = patientService.create(patient);

        assertThat(result.getNumeroDossier()).isEqualTo("DOS123");
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testFindById_Found() {
        Patient patient = new Patient();
        patient.setNumeroDossier("DOS123");

        PatientDto dto = new PatientDto("Nom", "Prenom", "email@test.com", "0600000000",
                LocalDate.of(1990, 1, 1), "DOS123", 5L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.toDto(patient)).thenReturn(dto);

        PatientDto result = patientService.findById(1L);

        assertThat(result.getNom()).isEqualTo("Nom");
        assertThat(result.getNumeroDossier()).isEqualTo("DOS123");
    }

    @Test
    void testFindById_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            patientService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Patient non trouvé avec id 1");
        }
    }

    @Test
    void testFindAll() {
        Patient p1 = new Patient();
        p1.setNumeroDossier("DOS001");
        Patient p2 = new Patient();
        p2.setNumeroDossier("DOS002");

        PatientDto dto1 = new PatientDto("Nom1", "Prenom1", "email1@test.com", "0600000001",
                LocalDate.of(1991, 1, 1), "DOS001", 2L);
        PatientDto dto2 = new PatientDto("Nom2", "Prenom2", "email2@test.com", "0600000002",
                LocalDate.of(1992, 2, 2), "DOS002", 3L);

        when(patientRepository.findAll()).thenReturn(java.util.Arrays.asList(p1, p2));
        when(patientMapper.toDto(p1)).thenReturn(dto1);
        when(patientMapper.toDto(p2)).thenReturn(dto2);

        var result = patientService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("nom").containsExactly("Nom1", "Nom2");
    }

    @Test
    void testUpdatePatient() {
        Patient patient = new Patient();
        patient.setNumeroDossier("DOS123");

        PatientDto dto = new PatientDto("NomModif", "PrenomModif", "email@test.com", "0600000000",
                LocalDate.of(1990, 1, 1), "DOS123", 4L);

        Patient updatedPatient = new Patient();
        updatedPatient.setNumeroDossier("DOS123");

        PatientDto updatedDto = new PatientDto("NomModif", "PrenomModif", "email@test.com", "0600000000",
                LocalDate.of(1990, 1, 1), "DOS123", 4L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(updatedPatient);
        when(patientMapper.toDto(updatedPatient)).thenReturn(updatedDto);

        PatientDto result = patientService.update(1L, dto);

        assertThat(result.getNom()).isEqualTo("NomModif");
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testUpdatePatient_NotFound() {
        PatientDto dto = new PatientDto("NomModif", "PrenomModif", "email@test.com", "0600000000",
                LocalDate.of(1990, 1, 1), "DOS123", 4L);

        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            patientService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Patient non trouvé avec id 1");
        }
    }

    @Test
    void testDeletePatient_Success() {
        when(patientRepository.existsById(1L)).thenReturn(true);

        patientService.delete(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePatient_NotFound() {
        when(patientRepository.existsById(1L)).thenReturn(false);

        try {
            patientService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Patient non trouvé avec id 1");
        }
    }
}
