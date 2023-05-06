package es.clubnautico.data.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.clubnautico.data.app.entities.Socio;

public interface SocioDao extends JpaRepository<Socio, Long> {

	List<Socio> findBynombreIgnoreCase(String nombre);
	

}
