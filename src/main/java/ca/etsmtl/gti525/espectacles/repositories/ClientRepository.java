package ca.etsmtl.gti525.espectacles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.etsmtl.gti525.espectacles.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Client findByUsername(String username);
	
}
