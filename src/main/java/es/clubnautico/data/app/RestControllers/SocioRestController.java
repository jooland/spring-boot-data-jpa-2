package es.clubnautico.data.app.RestControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.clubnautico.data.app.controllers.SocioController;
import es.clubnautico.data.app.entities.Socio;

@RestController
@RequestMapping("/api")
public class SocioRestController {

	@Autowired
	private SocioController socioController;

	@GetMapping("/socio/listar")
	public ResponseEntity<List<Socio>> listar() {
		// return ResponseEntity.ok(socioController.listarSocios());
		try {
			// List<Socio socios = socioController.listarSocios();
			return ResponseEntity.ok(socioController.listarSocios());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/socio")
	public ResponseEntity<Socio> crear(@RequestBody Socio socio) {
		try {
			Socio socioCreado = socioController.guardarSocio(socio);
			return ResponseEntity.status(HttpStatus.CREATED).body(socioCreado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/socio/{id}")
	public ResponseEntity<Socio> actualizar(@RequestBody Socio socio, @PathVariable Long id) {
		
		
		try {
			Socio actualizado = socioController.actualizarSocio(socio, id);
			return ResponseEntity.ok(actualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		/*Optional<Socio> socioId = socioController.buscarPorId(id);
		if (socioId.isPresent()) {
			Socio socioActual = socioId.get();
			socioActual.setNombre(socio.getNombre());
			socioActual.setApellido(socio.getApellido());
			socioActual.setEmail(socio.getEmail());
			socioActual.setTelefono(socio.getTelefono());
			Socio socioActualizado = socioController.guardarSocio(socioActual);
			return ResponseEntity.ok(socioActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}*/
	}

	
	@DeleteMapping("/socio/{id}")
	public ResponseEntity<Void> borrarPorId(@PathVariable Long id) {
		Optional<Socio> socio = socioController.buscarPorId(id);
		if (socio.isPresent()) {
			// Socio socioborrar = socio.get();
			socioController.borrarPorId(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/socio/{id}")
	public ResponseEntity<Socio> buscar(@PathVariable("id") Long id) {
		Optional<Socio> socio = socioController.buscarPorId(id);
		if (socio.isPresent()) {
			return ResponseEntity.ok(socio.get());
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
