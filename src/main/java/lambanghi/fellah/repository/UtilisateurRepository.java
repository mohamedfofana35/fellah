package lambanghi.fellah.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lambanghi.fellah.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
}