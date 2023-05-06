package es.clubnautico.data.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "salidas")
public class Salida implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSalida;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaSalida;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date horaSalida;

	private String destino;

	// id barco e id patron
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name = "idBarco")
	private Barco barco;

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name = "idPatron")
	private Patron patron;

	public Salida() {

	}

	public Salida(Long idSalida, Date fechaSalida, Date horaSalida, String destino, Barco barco, Patron patron) {
		super();
		this.idSalida = idSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.destino = destino;
		this.barco = barco;
		this.patron = patron;
	}

	public Long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Long idSalida) {
		this.idSalida = idSalida;
	}

	
	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	@Override
	public String toString() {
		return "Salida [idSalida=" + idSalida + ", fechaSalida=" + fechaSalida + ", horaSalida=" + horaSalida
				+ ", destino=" + destino + ", barco=" + barco + ", patron=" + patron + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(barco, idSalida, patron);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salida other = (Salida) obj;
		return Objects.equals(barco, other.barco) && Objects.equals(idSalida, other.idSalida)
				&& Objects.equals(patron, other.patron);
	}

}
