package com.fantagame.be.business.data.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fantagame.be.business.data.bean.Iface.IBean;

@Entity
@Table(name="persona")
@XmlRootElement(name = "Persona")
public class Persona implements Serializable,IBean<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5038444456643233388L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="idUser")
	private Integer idUser;
	
	@Column(name="cognome")
	private String cognome;
	 
	@Column(name="nome")
	private String nome;
	
	
	@Column(name="sesso",nullable=true)
	private String sesso;
	
	@Column(name="dataNascita")
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataNascita;
	
	@Column(name="dataCreation")
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataCreation;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="accCond")
	private Boolean accCond;
	
	@OneToOne(mappedBy="persona")
	@JsonIgnore
	private UserLogin userLogin;
	
	public Persona() {
		super();
	}

	public Persona(int idUser, String cognome, String nome, String sesso,
			Date dataNascita, String email, Boolean accCond,UserLogin userLogin) {
		super();
		this.idUser = idUser;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.email = email;
		this.accCond = accCond;
		this.userLogin = userLogin;
	}

	public Persona(int idUser, String cognome, String nome, String sesso,
			Date dataNascita, String email, String telefono, Boolean accCond,UserLogin userLogin) {
		super();
		this.idUser = idUser;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.email = email;
		this.telefono = telefono;
		this.accCond = accCond;
		this.userLogin = userLogin;

	}

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the dataNascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the accCond
	 */
	public Boolean getAccCond() {
		return accCond;
	}

	/**
	 * @param accCond the accCond to set
	 */
	public void setAccCond(Boolean accCond) {
		this.accCond = accCond;
	}
			

	public Date getDataCreation() {
		return dataCreation;
	}

	public void setDataCreation(Date dataCreation) {
		this.dataCreation = dataCreation;
	}

	/**
	 * @return the userLogin
	 */
	public UserLogin getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [idUser=" + idUser + ", cognome=" + cognome + ", nome="
				+ nome + ", sesso=" + sesso + ", dataNascita=" + dataNascita
				+ ", email=" + email + ", telefono=" + telefono + ", accCond="
				+ accCond + ", userLogin=" + userLogin.getNick() +  "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accCond == null) ? 0 : accCond.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result	+ ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (accCond == null) {
			if (other.accCond != null)
				return false;
		} else if (!accCond.equals(other.accCond))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sesso != other.sesso)
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return idUser;
	}
	
	
	

}
