package es.clubnautico.data.app.entities;

import java.io.Serializable;
import java.util.Objects;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name="patrones")
public class Patron implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPatron;
	
	private String nombre;
	private String apellido;
	@Email
	private String email;
	private Long telefono;
	
	public Patron() {
	
	}

	public Patron(Long idPatron, String nombre, String apellido, @Email String email, Long telefono) {
		super();
		this.idPatron = idPatron;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}

	
	public Patron(Long idPatron, String nombre, String apellido, @Email String email) {
	
		this.idPatron = idPatron;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email=email;
	}

	public Long getIdPatron() {
		return idPatron;
	}

	public void setIdPatron(Long idPatron) {
		this.idPatron = idPatron;
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

	@Override
	public String toString() {
		return "Patron [idPatron=" + idPatron + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPatron);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patron other = (Patron) obj;
		return Objects.equals(idPatron, other.idPatron);
	}

	private static final long serialVersionUID = 1L;
}

