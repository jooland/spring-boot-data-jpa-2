package es.clubnautico.data.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.clubnautico.data.app.entities.Barco;

public interface BarcoDao extends JpaRepository<Barco, Long> {
	
	 List<Barco> findAllByOrderByNombreAsc();
}
