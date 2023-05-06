package es.clubnautico.data.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.clubnautico.data.app.entities.Socio;
import es.clubnautico.data.app.repositories.SocioDao;

@Controller
public class SocioController {

	@Autowired
	private SocioDao socioDao;

	public List<Socio> listarSocios() {

		return socioDao.findAll();
	}

	public Socio guardarSocio(Socio socio) {

		return socioDao.save(socio);
	}

	public Optional<Socio> buscarPorId(Long id) {
		
		return socioDao.findById(id);
	}
	
    public void borrarPorId(Long id) {
		
		socioDao.deleteById(id);
	}
	
}

	/*public Socio actualizarSocio(Socio socio, Long id) {
		Optional<Socio> socioId = socioDao.findById(id);

		if (socioId.isPresent()) {
			Socio socioActual = socioId.get();
			socioActual.setNombre(socio.getNombre());
			socioActual.setApellido(socio.getApellido());
			socioActual.setEmail(socio.getEmail());
			socioActual.setTelefono(socio.getTelefono());
			Socio socioActualizado = socioDao.save(socioActual);
			return socioActualizado;
		} else {
			return null;
		}
	  }*/


