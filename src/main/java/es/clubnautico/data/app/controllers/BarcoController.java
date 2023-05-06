package es.clubnautico.data.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.clubnautico.data.app.repositories.BarcoDao;
import es.clubnautico.data.app.repositories.SocioDao;

@Controller
public class BarcoController {

	@Autowired
	private BarcoDao barcoDao;
	
	@Autowired
	private SocioDao socioDao;
	
	public 
}
