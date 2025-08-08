package lambanghi.fellah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lambanghi.fellah.entity.Medecin;
import lambanghi.fellah.entity.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    boolean existsByMedecinAndDateHeure(Medecin medecin, java.time.LocalDateTime dateHeure);

}
