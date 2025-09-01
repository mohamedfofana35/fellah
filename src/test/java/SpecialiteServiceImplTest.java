import lambanghi.fellah.dto.SpecialiteDto;
import lambanghi.fellah.entity.Specialite;
import lambanghi.fellah.mapper.SpecialiteMapper;
import lambanghi.fellah.repository.SpecialiteRepository;
import lambanghi.fellah.service.impl.SpecialiteServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialiteServiceImplTest {

    @Mock
    private SpecialiteRepository specialiteRepository;

    @Mock
    private SpecialiteMapper specialiteMapper;

    @InjectMocks
    private SpecialiteServiceImpl specialiteService;

    @Test
    void testCreateSpecialite() {
        Specialite specialite = new Specialite(null, "Cardiologie", null);
        Specialite savedSpecialite = new Specialite(1L, "Cardiologie", null);

        when(specialiteRepository.save(specialite)).thenReturn(savedSpecialite);

        Specialite result = specialiteService.create(specialite);

        assertThat(result.getId()).isEqualTo(1L);
        verify(specialiteRepository, times(1)).save(specialite);
    }

    @Test
    void testFindById_Found() {
        Specialite specialite = new Specialite(1L, "Cardiologie", null);
        SpecialiteDto dto = new SpecialiteDto(1L, "Cardiologie");

        when(specialiteRepository.findById(1L)).thenReturn(Optional.of(specialite));
        when(specialiteMapper.toDto(specialite)).thenReturn(dto);

        SpecialiteDto result = specialiteService.findById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Cardiologie");
    }

    @Test
    void testFindById_NotFound() {
        when(specialiteRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            specialiteService.findById(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Spécialité non trouvée avec id 1");
        }
    }

    @Test
    void testFindAll() {
        Specialite s1 = new Specialite(1L, "Cardiologie", null);
        Specialite s2 = new Specialite(2L, "Dermatologie", null);
        SpecialiteDto dto1 = new SpecialiteDto(1L, "Cardiologie");
        SpecialiteDto dto2 = new SpecialiteDto(2L, "Dermatologie");

        when(specialiteRepository.findAll()).thenReturn(java.util.Arrays.asList(s1, s2));
        when(specialiteMapper.toDto(s1)).thenReturn(dto1);
        when(specialiteMapper.toDto(s2)).thenReturn(dto2);

        var result = specialiteService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("nom").containsExactly("Cardiologie", "Dermatologie");
    }

    @Test
    void testUpdateSpecialite() {
        Specialite specialite = new Specialite(1L, "Cardiologie", null);
        SpecialiteDto dto = new SpecialiteDto(1L, "Neurologie");
        Specialite updatedSpecialite = new Specialite(1L, "Neurologie", null);
        SpecialiteDto updatedDto = new SpecialiteDto(1L, "Neurologie");

        when(specialiteRepository.findById(1L)).thenReturn(Optional.of(specialite));
        when(specialiteRepository.save(specialite)).thenReturn(updatedSpecialite);
        when(specialiteMapper.toDto(updatedSpecialite)).thenReturn(updatedDto);

        SpecialiteDto result = specialiteService.update(1L, dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Neurologie");
        verify(specialiteRepository, times(1)).save(specialite);
    }

    @Test
    void testUpdateSpecialite_NotFound() {
        SpecialiteDto dto = new SpecialiteDto(1L, "Neurologie");
        when(specialiteRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            specialiteService.update(1L, dto);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Spécialité non trouvée avec id 1");
        }
    }

    @Test
    void testDeleteSpecialite_Success() {
        when(specialiteRepository.existsById(1L)).thenReturn(true);

        specialiteService.delete(1L);

        verify(specialiteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSpecialite_NotFound() {
        when(specialiteRepository.existsById(1L)).thenReturn(false);

        try {
            specialiteService.delete(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class)
                         .hasMessageContaining("Spécialité non trouvée avec id 1");
        }
    }
}