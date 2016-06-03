package ca.etsmtl.gti525.espectacles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.etsmtl.gti525.espectacles.domain.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

	public Paiement findById(Long id);
	
	
}
