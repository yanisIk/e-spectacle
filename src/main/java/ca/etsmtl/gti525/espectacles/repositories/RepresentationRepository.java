package ca.etsmtl.gti525.espectacles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.etsmtl.gti525.espectacles.domain.Representation;
import ca.etsmtl.gti525.espectacles.domain.Salle;

public interface RepresentationRepository extends JpaRepository<Representation, Long> {

	public List<Representation> findBySalle(Salle salle);

	public Representation findByNom(String name);
	
}
