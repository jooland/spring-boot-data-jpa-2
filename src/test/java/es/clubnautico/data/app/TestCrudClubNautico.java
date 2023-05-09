package es.clubnautico.data.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import es.clubnautico.data.app.entities.Barco;
import es.clubnautico.data.app.entities.Patron;
import es.clubnautico.data.app.entities.Salida;
import es.clubnautico.data.app.entities.Socio;
import es.clubnautico.data.app.repositories.BarcoDao;
import es.clubnautico.data.app.repositories.PatronDao;
import es.clubnautico.data.app.repositories.SalidaDao;
import es.clubnautico.data.app.repositories.SocioDao;
//import jakarta.validation.constraints.Email;
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestCrudClubNautico {
	@Autowired
	private SocioDao socioDao;
	@Autowired
	private BarcoDao barcoDao;
	@Autowired
	private PatronDao patronDao;
	@Autowired
	private SalidaDao salidaDao;
	
	
	@Test
	@Rollback(false)
	public void testCrearSocio() {
		
		//GIVEN
		Socio socio = new Socio(null, "whisky","tapia", "tapia@chela.com", 963852741L);
		socio.setItemBarco(new ArrayList<>());
		//WHEN		
		Socio socioGuardar = socioDao.save(socio);
		//THEN
		assertNotNull(socioGuardar);
		assertEquals("whisky",socioGuardar.getNombre());
		assertEquals("tapia@chela.com",socioGuardar.getEmail());
		
	}
	
	@Test
	@Rollback(false)
	public void TestActualizarSocio() {
		
		Optional<Socio> socio = socioDao.findById(5L);
		Socio socioId= socio.get();
		
		socioId.setNombre("Trump");
		socioId.setEmail("trump@hotmail.com");
		Socio buscar=socioDao.save(socioId);
		assertNotNull(buscar);
		assertEquals("Trump",buscar.getNombre());
		assertEquals("trump@hotmail.com",buscar.getEmail());
	}
	
	@Test
	@Rollback(false)
	public void TestCrearBarco() {
	//	Optional<Socio> socio = socioDao.findById(1L);
		//Socio socioBarco = socio.get();
		Barco barco1 = new Barco(null, 501L, "mosca",154,344l,true);
		barco1=barcoDao.save(barco1);
		assertNotNull(barco1);
		
		
	}
	
	@Test
	@Rollback(false)
	public void TestAgregarBarcoAlSocio() {
		Optional<Socio> socio = socioDao.findById(4L);
		Optional<Barco> barco = barcoDao.findById(2L);
		Socio socioBarco = socio.get();
		Barco barcoitem = barco.get();
 		
		socioBarco.addItemBarco(barcoitem);
		Socio buscar=socioDao.save(socioBarco);
		assertNotNull(buscar);
		assertTrue(buscar.getItemBarco().contains(barcoitem));		
	}
	
	@Test
	@Rollback(false)
	public void TestActualizarBarco() {
		Optional<Barco> barco= barcoDao.findById(1L);
		Barco barcoId=barco.get();
		barcoId.setMatricula(4520L);
		barcoId.setCuota(100L);
		
		Barco buscar = barcoDao.save(barcoId);
		assertNotNull(buscar);
		assertEquals(4520L,buscar.getMatricula());
		assertEquals(100L,buscar.getCuota());
		
	}
	
	@Test
	@Rollback(false)
	public void TestCrearPatron() {
		Patron patron = new Patron(null, "rocky", "castro","rocky@hotmail.com"); 
		Patron patronGuardar=patronDao.save(patron);
		assertNotNull(patronGuardar.getIdPatron());
		assertEquals("castro",patronGuardar.getApellido());
	}
	@Test
	@Rollback(false)
	public void TestBuscarPatronPorEmail() {
	    Patron patron = new Patron(null, "Juan", "Perez", "juan.perez@hotmail.com");
	    patronDao.save(patron);
	    Optional<Patron> patronEmail = patronDao.findByEmail("juan.perez@hotmail.com");
	    assertNotNull(patronEmail.get());
	    assertEquals("Juan", patron.getNombre());
	    assertEquals("Perez", patron.getApellido());
	}
	
	
	@Test
	@Rollback(false)
	public void TestActualizarPatron() {
		Optional<Patron> patron = patronDao.findById(2L);
		Patron patronActualizado = patron.get();
		patronActualizado.setNombre("El Papi");
		patronActualizado.setTelefono(945875478L);
		Patron buscar=patronDao.save(patronActualizado);
		assertNotNull(buscar);
	    assertEquals("El Papi", buscar.getNombre());
	    assertEquals(945875478L, buscar.getTelefono());
	}
	
	@Test
	@Rollback(false)
	public void testCrearSalida() throws ParseException {
	    
	    Optional<Patron> patron = patronDao.findById(2L);
	    Optional<Barco> barco = barcoDao.findById(2L);
	    Patron patronId = patron.get();
	    Barco barcoId = barco.get();
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaSalida = dateFormat.parse("01/05/2023");
	    DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
	    Date horaSalida = horaFormat.parse("10:00:00");
	    Salida salida = new Salida(null, fechaSalida, horaSalida, "Canada", barcoId, patronId);
	    
	    Salida guardado=salidaDao.save(salida);
	    assertNotNull(salida);
	    Salida buscar = salidaDao.findById(salida.getIdSalida()).orElse(null);
	   
	    assertNotNull(buscar);
	    assertEquals(buscar.getFechaSalida(), guardado.getFechaSalida());
	    assertEquals(buscar.getHoraSalida(), guardado.getHoraSalida());
	    assertEquals(buscar.getDestino(), guardado.getDestino());
	    assertEquals(buscar.getBarco(), guardado.getBarco());
	    assertEquals(buscar.getPatron(),guardado.getPatron());
	}

	@Test
	@Rollback(false)
	public void TestActualizarSalida() throws ParseException {
		Optional<Salida> salida = salidaDao.findById(2L);
		Salida salidaId=salida.get();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaSalida = dateFormat.parse("20/05/2023");
	    DateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
	    Date horaSalida = horaFormat.parse("17:30:15");
		salidaId.setFechaSalida(fechaSalida);
		salidaId.setHoraSalida(horaSalida);
		salidaId.setDestino("Australia");
		Salida guardado =salidaDao.save(salidaId);
		assertNotNull(guardado);
		Salida buscar = salidaDao.findById(salidaId.getIdSalida()).orElse(null);
		assertNotNull(buscar);
		
		assertEquals(buscar.getFechaSalida(),guardado.getFechaSalida());
		assertEquals(buscar.getDestino(),guardado.getDestino());
		 
	}
	/*******************TEST CONSULTAS******************************/
	@Test
	@Rollback(false)
	public void TestbuscaIgnoradoMayusMinusSocio() {
		assertNotNull(socioDao.findBynombreIgnoreCase("LORENZO"));
		
	}
	
	@Test
	@Rollback(false)
	public void TestSocioBuscarTodos() {
		List<Socio> socio = socioDao.findAll();
		Long cantidaRegistros=socioDao.count();
		assertEquals(cantidaRegistros,socio.size());
	}
	
	@Test
	@Rollback(false)
	public void TestSalidasBuscarTodos() {
		List<Salida> salida = salidaDao.findAll();
		Long cantidaRegistros=salidaDao.count();
		assertEquals(cantidaRegistros,salida.size());
	}
	
	@Test
	@Rollback(false)
	public void TestBuscarSalidasEnRangoFecha() throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date fechaInicial = dateFormat.parse("01/01/2023");
	   
	    Date fechaFinal = dateFormat.parse("20/05/2023");
		List<Salida> listaSalidas = salidaDao.findByfechaSalidaBetween(fechaInicial,fechaFinal);
		assertNotNull(listaSalidas);
		  //Salida salida1 = listaSalidas.get(0);
		  assertEquals("Canada", listaSalidas.get(0).getDestino());
		  assertEquals("Asutralia", listaSalidas.get(1).getDestino());
	}
	/*******************TEST DE BORRADOS****************************/
	@Test
	@Rollback(false)
	public void TestBorrarSalida() {
		salidaDao.deleteById(2L);
		Optional<Salida> salida = salidaDao.findById(2L);
		assertFalse(salida.isPresent());
		
	}
	
	@Test
	@Rollback(false)
	public void TestBorrarSocio() {
		socioDao.deleteById(1L);
		Optional<Socio> socio = socioDao.findById(1L);
		assertFalse(socio.isPresent());
		
	}
	
	@Test
	@Rollback(false)
	public void TestBorrarBarco() {
		barcoDao.deleteById(1L);
		Optional<Barco> barco = barcoDao.findById(1L);
		assertFalse(barco.isPresent());
		
	}
	
	@Test
	@Rollback(false)
	public void TestBorrarPatron() {
		patronDao.deleteById(1L);
		Optional<Patron> patron = patronDao.findById(1L);
		assertFalse(patron.isPresent());
		
	}
}
