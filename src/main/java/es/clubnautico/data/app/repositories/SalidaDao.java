package es.clubnautico.data.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.clubnautico.data.app.entities.Salida;

public interface SalidaDao extends JpaRepository<Salida, Long> {

	List<Salida> findByfechaSalidaBetween(Date Finicial, Date Ffinal);

}
