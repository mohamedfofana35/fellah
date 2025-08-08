package lambanghi.fellah.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lambanghi.fellah.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNumeroDossier(String numeroDossier);
}