package es.golemdr.prefieromizona.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "idRol", scope = Rol.class)
@Entity
@Table(name="roles")
public class Rol implements Serializable{

	private static final long serialVersionUID = -8085723214863186284L;
	private Long idRol;
	private String nombreRol;

	@JsonIgnore
	private List<Usuario> usuarios = new ArrayList<>(0);

	@Id
	@Column(name = "ID_ROL")
	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	@Column(name = "NOMBRE_ROL")
	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
