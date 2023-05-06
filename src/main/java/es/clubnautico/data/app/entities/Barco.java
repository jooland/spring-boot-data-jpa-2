package es.clubnautico.data.app.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="barcos")
public class Barco implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idBarco;
	
	@Column(unique=true)
	private Long matricula;
	
	private String nombre;
	private int numAmarre;
	private float cuota;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean activo;
	
	public Barco() {
		
	}
	
	public Barco(Long idBarco, Long matricula, String nombre, int numAmarre, float cuota, boolean activo) {
		
		this.idBarco = idBarco;
		this.matricula = matricula;
		this.nombre = nombre;
		this.numAmarre = numAmarre;
		this.cuota = cuota;
		this.activo = activo;
	}
	

	public Barco(Long idBarco, Long matricula, String nombre, boolean activo) {
		
		this.idBarco = idBarco;
		this.matricula = matricula;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Long getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumAmarre() {
		return numAmarre;
	}

	public void setNumAmarre(int numAmarre) {
		this.numAmarre = numAmarre;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Barco [idBarco=" + idBarco + ", matricula=" + matricula + ", nombre=" + nombre + ", numAmarre="
				+ numAmarre + ", cuota=" + cuota + ", activo=" + activo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idBarco, matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barco other = (Barco) obj;
		return Objects.equals(idBarco, other.idBarco) && Objects.equals(matricula, other.matricula);
	}

	private static final long serialVersionUID = 1L;

	
}
