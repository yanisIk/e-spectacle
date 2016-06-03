package ca.etsmtl.gti525.espectacles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ca.etsmtl.gti525.espectacles.domain.CategoriesSpectacle;
import ca.etsmtl.gti525.espectacles.domain.Spectacle;

public interface SpectacleRepository extends JpaRepository<Spectacle, Long> {

	
	List<Spectacle> findByDescriptionContaining(String description);

	/**
	 * Requete pour rechercher un spectacle
	 * @param attribut1 == attribut2
	 * @return liste de spectacles
	 */
	List<Spectacle> findByDescriptionContainingOrNomContainingAllIgnoreCase(String attribut, String attribut2);

	Spectacle findByNom(String nom);

	List<Spectacle> findByCategorie(CategoriesSpectacle category);

	
}
