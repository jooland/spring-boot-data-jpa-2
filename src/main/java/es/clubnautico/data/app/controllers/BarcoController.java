package es.clubnautico.data.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.clubnautico.data.app.entities.Barco;
import es.clubnautico.data.app.entities.Socio;
import es.clubnautico.data.app.repositories.BarcoDao;
import es.clubnautico.data.app.repositories.SocioDao;

@Controller
public class BarcoController {

	@Autowired
	private BarcoDao barcoDao;
	
	@Autowired
	private SocioDao socioDao;
	
		public List<Barco> listarBarcos() {

		return barcoDao.findAll();
		}
		
		public List<Barco> listarBarcosPorNombre() {
			
			return barcoDao.findAllByOrderByNombreAsc();
		}
		
	    public List<Barco> actualizarBarcos(Long id, List<Barco> barcosActualizados) {
	        Optional<Socio> socioExistente = socioDao.findById(id);
	        if (socioExistente.isPresent())
	        {	Socio socio = socioExistente.get();
	            // Actualizar barcos existentes y agregar nuevos barcos
	            List<Barco> barcosExistentes = socio.getItemBarco();
	            for (Barco barcoActualizado : barcosActualizados) {
	                boolean encontrado = false;
	                for (Barco barcoExistente : barcosExistentes) {
	                    if (barcoExistente.getIdBarco().equals(barcoActualizado.getIdBarco())) 
	                    {	
	                        encontrado = true;
	                        if (barcoActualizado.isActivo()) {
	                            barcoExistente.setActivo(true);
	                        } else {
	                            barcoExistente.setNombre(barcoActualizado.getNombre());
	                            barcoExistente.setMatricula(barcoActualizado.getMatricula());
	                            barcoExistente.setNumAmarre(barcoActualizado.getNumAmarre());
	                            barcoExistente.setCuota(barcoActualizado.getIdBarco());
	                           
	                        }
	                        break;
	                    }
	                }
	                if (!encontrado && !barcoActualizado.isActivo()) {
	                    //barcoActualizado.setSocio(socio);
	                	 Barco barcoAGuardar = new Barco();
	         	         barcoAGuardar.setMatricula(barcoActualizado.getMatricula());
	         	         barcoAGuardar.setNombre(barcoActualizado.getNombre());
	         	         barcoAGuardar.setNumAmarre(barcoActualizado.getNumAmarre());
	         	         barcoAGuardar.setCuota(barcoActualizado.getCuota());
	         	         barcoDao.save(barcoAGuardar);
	                    barcosExistentes.add(barcoActualizado);
	                }
	            }
	            
	            //socioService.actualizarSocio(socio);
	            socio.setItemBarco(barcosExistentes);;
	            socioDao.save(socio);	
	            return barcosExistentes;
	        } else {
	        	return null;
	            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
	        }
	    }
	    
	    public void eliminarBarcosMarcados(Long socioId) {
	        Optional<Socio> sociobuscado = socioDao.findById(socioId);
	        Socio socio = sociobuscado.get();
	  
	        List<Barco> barcos = socio.getItemBarco();
	        List<Barco> barcosEliminados = new ArrayList<>();
	        for (Barco barco : barcos) {
	            if (barco.isActivo()) {
	                barcosEliminados.add(barco);
	            }
	        }
	        socio.getItemBarco().removeAll(barcosEliminados);
	        socioDao.save(socio);
	        barcoDao.deleteAll(barcosEliminados);
	    }
	    
	}


