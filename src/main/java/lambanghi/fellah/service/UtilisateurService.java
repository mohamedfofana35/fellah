package lambanghi.fellah.service;

public interface UtilisateurService {
	createUtilisateur(UtilisateurDto dto);

	findById(Long id);

	findAll();

	updateUtilisateur(Long id, UtilisateurDto dto);

	deleteUtilisateur(Long id);
}
