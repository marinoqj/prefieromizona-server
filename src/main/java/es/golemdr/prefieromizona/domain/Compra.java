package es.golemdr.prefieromizona.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "idCompra", scope = Compra.class)
@Entity
@Table(name="compras")
public class Compra{

	private Long idCompra;
	private Date fechaCompra;
	private Long puntos;
	private Date fechaCanje;
	private Long idComercio;
	private Long idCliente;



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID_COMPRA")
public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}
@Column(name="FECHA_COMPRA")
public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
@Column(name="PUNTOS")
public Long getPuntos() {
		return puntos;
	}
	public void setPuntos(Long puntos) {
		this.puntos = puntos;
	}
@Column(name="FECHA_CANJE")
public Date getFechaCanje() {
		return fechaCanje;
	}
	public void setFechaCanje(Date fechaCanje) {
		this.fechaCanje = fechaCanje;
	}
@Column(name="ID_COMERCIO")
public Long getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}
@Column(name="ID_CLIENTE")
public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}
