package es.golemdr.prefieromizona.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario", scope = Usuario.class)
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3363285127576802253L;
	private Long idUsuario;
	private String login;
	private String password;
	private String cambiarPassword;

	private List<Rol> roles = new ArrayList<>(0);
	
	private Cliente cliente;
	private Comercio comercio;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "CAMBIAR_PASSWORD")
	public String getCambiarPassword() {
		return cambiarPassword;
	}

	public void setCambiarPassword(String cambiarPassword) {
		this.cambiarPassword = cambiarPassword;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_usuarios", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_rol") })
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	@OneToOne(mappedBy = "usuario")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToOne(mappedBy = "usuario")
	public Comercio getComercio() {
		return comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	

}
