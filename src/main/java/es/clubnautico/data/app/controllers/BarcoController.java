package es.clubnautico.data.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.clubnautico.data.app.entities.Barco;
import es.clubnautico.data.app.repositories.BarcoDao;
import es.clubnautico.data.app.repositories.SocioDao;

@Controller
public class BarcoController {

	@Autowired
	private BarcoDao barcoDao;
	
	@Autowired
	private SocioDao socioDao;
	
	    public Socio actualizarBarcos(Long id, List<Barco> barcosActualizados) {
	        Socio socio = socioDao.findById(id);
	        if (socio != null) {
	            // Actualizar barcos existentes y agregar nuevos barcos
	            List<Barco> barcosExistentes = socio.getBarcos();
	            for (Barco barcoActualizado : barcosActualizados) {
	                boolean encontrado = false;
	                for (Barco barcoExistente : barcosExistentes) {
	                    if (barcoExistente.getId().equals(barcoActualizado.getId())) {
	                        encontrado = true;
	                        if (barcoActualizado.isEliminado()) {
	                            barcoExistente.setEliminado(true);
	                        } else {
	                            barcoExistente.setNombre(barcoActualizado.getNombre());
	                            barcoExistente.setDescripcion(barcoActualizado.getDescripcion());
	                            // otros atributos
	                        }
	                        break;
	                    }
	                }
	                if (!encontrado && !barcoActualizado.isEliminado()) {
	                    barcoActualizado.setSocio(socio);
	                    
	                    barcosExistentes.add(barcoActualizado);
	                }
	            }
	            socio.setBarcos(barcosExistentes);
	            socioService.actualizarSocio(socio);
	            return socio;
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
	        }
	    }
	}

}
