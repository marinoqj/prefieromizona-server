package es.golemdr.prefieromizona.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "idComercio", scope = Comercio.class)
@Entity
@Table(name="comercios")
public class Comercio{

	private Long idComercio;
	private String codComercio;
	private String razonSocial;
	private String cif;
	private String nombreResponsable;
	private String apellido1Responsable;
	private String apellido2Responsable;
	private String direccion;
	private String municipio;
	private String codPostal;



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID_COMERCIO")
public Long getIdComercio() {
		return idComercio;
	}
	public void setIdComercio(Long idComercio) {
		this.idComercio = idComercio;
	}
@Column(name="COD_COMERCIO")
public String getCodComercio() {
		return codComercio;
	}
	public void setCodComercio(String codComercio) {
		this.codComercio = codComercio;
	}
@Column(name="RAZON_SOCIAL")
public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
@Column(name="CIF")
public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
@Column(name="NOMBRE_RESPONSABLE")
public String getNombreResponsable() {
		return nombreResponsable;
	}
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
@Column(name="APELLIDO1_RESPONSABLE")
public String getApellido1Responsable() {
		return apellido1Responsable;
	}
	public void setApellido1Responsable(String apellido1Responsable) {
		this.apellido1Responsable = apellido1Responsable;
	}
@Column(name="APELLIDO2_RESPONSABLE")
public String getApellido2Responsable() {
		return apellido2Responsable;
	}
	public void setApellido2Responsable(String apellido2Responsable) {
		this.apellido2Responsable = apellido2Responsable;
	}
@Column(name="DIRECCION")
public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
@Column(name="MUNICIPIO")
public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
@Column(name="COD_POSTAL")
public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

}
