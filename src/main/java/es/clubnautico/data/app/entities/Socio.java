package es.clubnautico.data.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="socios")
public class Socio implements Serializable {
    
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idSocio;
	private String nombre;
	private String apellido;
	@Email
	private String email;
	private Long telefono;
	
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="idSocio")
	@NotNull
	private List<Barco> ItemBarco;
	
	
	public Socio() {
	this.ItemBarco = new ArrayList<>();
	}


	public Socio(Long idSocio, String nombre, String apellido, @Email String email, Long telefono) {
		
		this.idSocio = idSocio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}


	/*public Socio(Long idSocio, String nombre, String apellido, @Email String email, Long telefono,
			@NotNull List<Barco> itemBarco) {
		
		this.idSocio = idSocio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		ItemBarco = itemBarco;
	}*/


	public Long getIdSocio() {
		return idSocio;
	}


	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getTelefono() {
		return telefono;
	}


	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}


	public List<Barco> getItemBarco() {
		return ItemBarco;
	}


	public void setItemBarco(List<Barco> itemBarco) {
		ItemBarco = itemBarco;
	}
	public void addItemBarco(Barco itembarco) {
		this.ItemBarco.add(itembarco);
	}

	@Override
	public String toString() {
		return "Socio [idSocio=" + idSocio + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(idSocio);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(idSocio, other.idSocio);
	}


	private static final long serialVersionUID = 1L;
	
	

}
