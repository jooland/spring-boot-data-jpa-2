package es.clubnautico.data.app.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.clubnautico.data.app.controllers.BarcoController;

import es.clubnautico.data.app.entities.Barco;
import es.clubnautico.data.app.entities.Socio;
import es.clubnautico.data.app.repositories.BarcoDao;
import es.clubnautico.data.app.repositories.SocioDao;

@RestController
@RequestMapping("/api/barco")
public class BarcoRestController {

	
	@Autowired
	private BarcoController barcoController; 
	
	@GetMapping("/listar")
	public ResponseEntity<List<Barco>> listar() {
		// return ResponseEntity.ok(socioController.listarSocios());
		try {
			// List<Socio socios = socioController.listarSocios();
			return ResponseEntity.ok(barcoController.listarBarcos());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/listarNombre")
	public ResponseEntity<List<Barco>> listarPorNombre() {
		// return ResponseEntity.ok(socioController.listarSocios());
		try {
			// List<Socio socios = socioController.listarSocios();
			return ResponseEntity.ok(barcoController.listarBarcosPorNombre());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/socio/{id}/barco")
    public ResponseEntity<List<Barco>> actualizarBarcos(@PathVariable Long id, @RequestBody List<Barco> barcosActualizados) {
		
		
		try {
			List<Barco>  actualizado = barcoController.actualizarBarcos(id,barcosActualizados);
			barcoController.eliminarBarcosMarcados(id);
			return ResponseEntity.ok(actualizado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	
	
	
	
	
	}
	
}
