package lambanghi.fellah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lambanghi.fellah.entity.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> { }
