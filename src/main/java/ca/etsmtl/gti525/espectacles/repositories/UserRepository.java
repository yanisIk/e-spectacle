package ca.etsmtl.gti525.espectacles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.etsmtl.gti525.espectacles.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
