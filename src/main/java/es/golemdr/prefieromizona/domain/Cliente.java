package es.golemdr.prefieromizona.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "idCliente", scope = Cliente.class)
@Entity
@Table(name="clientes")
public class Cliente{


	private Long idCliente;
	private String codCliente;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	
	@JsonIgnore	
	List<Compra> compras = new ArrayList<>(0);


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID_CLIENTE")
public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

@Column(name="COD_CLIENTE")
public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
@Column(name="NOMBRE")
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
@Column(name="APELLIDO1")
public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
@Column(name="APELLIDO2")
public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
@Column(name="DNI")
public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
@Column(name="TELEFONO")
public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
public List<Compra> getCompras() {
	return compras;
}
public void setCompras(List<Compra> compras) {
	this.compras = compras;
}
	


}
