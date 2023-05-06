package es.clubnautico.data.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.clubnautico.data.app.entities.Patron;

public interface PatronDao extends JpaRepository<Patron, Long> {

	Optional<Patron> findByEmail(String email);
	
	

}
