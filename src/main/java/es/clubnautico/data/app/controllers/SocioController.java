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
public class SocioController {

	@Autowired
	private SocioDao socioDao;
	
	@Autowired
	private BarcoDao barcoDao;

	public List<Socio> listarSocios() {

		return socioDao.findAll();
	}

	public Socio guardarSocio(Socio socio) {
		Socio socioAGuardar = new Socio();
	      socioAGuardar.setNombre(socio.getNombre());
	      socioAGuardar.setApellido(socio.getApellido());
	      socioAGuardar.setEmail(socio.getEmail());
	      socioAGuardar.setTelefono(socio.getTelefono());
	      
	      List<Barco> barcosAGuardar = new ArrayList<>();
	      for (Barco barco : socio.getItemBarco()) {
	         Barco barcoAGuardar = new Barco();
	         barcoAGuardar.setMatricula(barco.getMatricula());
	         barcoAGuardar.setNombre(barco.getNombre());
	         barcoAGuardar.setNumAmarre(barco.getNumAmarre());
	         barcoAGuardar.setCuota(barco.getCuota());
	         barcosAGuardar.add(barcoAGuardar);
	      }
	      
	      socioAGuardar.setItemBarco(barcosAGuardar);
	      socioDao.save(socioAGuardar);
	      
	      for (int i = 0; i < socio.getItemBarco().size(); i++) {
	          Barco barcoAGuardar = barcosAGuardar.get(i);
	          
	          barcoDao.save(barcoAGuardar);
	       }
	      
	      return socioAGuardar;
	}

	public Optional<Socio> buscarPorId(Long id) {
		
		return socioDao.findById(id);
	}
	
    public void borrarPorId(Long id) {
		
		socioDao.deleteById(id);
	}
    
    public Socio actualizarSocio(Socio socio, Long id) {
    
    	    Optional<Socio> socioOpt = socioDao.findById(id);
    	    if (socioOpt.isPresent()) {
    	        Socio socioActual = socioOpt.get();
    	        socioActual.setNombre(socio.getNombre());
    	        socioActual.setApellido(socio.getApellido());
    	        socioActual.setEmail(socio.getEmail());
    	        socioActual.setTelefono(socio.getTelefono());
    	   
    	        socioDao.save(socioActual);
    	        return socioActual;
    	    } else {
    	        return null;
    	    }
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
			 List<Barco> barcosAGuardar = new ArrayList<>();
	      for (Barco barco : socio.getItemBarco()) {
	         Barco barcoAGuardar = new Barco();
	         barcoAGuardar.setMatricula(barco.getMatricula());
	         barcoAGuardar.setNombre(barco.getNombre());
	         barcoAGuardar.setNumAmarre(barco.getNumAmarre());
	         barcoAGuardar.setCuota(barco.getCuota());
	         barcosAGuardar.add(barcoAGuardar);
	      }
	      
	      socioActual.setItemBarco(barcosAGuardar);
	      socioDao.save(socioAGuardar);
	      
	      for (int i = 0; i < socio.getItemBarco().size(); i++) {
	          Barco barcoAGuardar = barcosAGuardar.get(i);
	          
	          barcoDao.save(barcoAGuardar);
	      }
	       
	     return socioDao.save(socio); 
			
		} else {
			return null;
		}
	  }*/


