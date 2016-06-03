package ca.etsmtl.gti525.espectacles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.etsmtl.gti525.espectacles.domain.Billet;
import ca.etsmtl.gti525.espectacles.domain.Representation;

public interface BilletRepository  extends JpaRepository<Billet, Long>{
	
	Billet findByRepresentationId(Long repId);

}
