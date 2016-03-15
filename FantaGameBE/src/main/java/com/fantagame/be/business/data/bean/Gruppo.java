package com.fantagame.be.business.data.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.data.bean.Iface.IBean;

 
@Entity
@Table(name="gruppo")
@XmlRootElement(name = "Gruppo")
public class Gruppo implements Serializable,IBean<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4690336960840260684L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idGruppo")	 
	private Integer idGruppo;
	
	@Column(name="dataCreazione")
	private Date dataCreazione;
	
	@Column(name="maxCredito")
	private Integer maxCredito; 
	
	@Column(name="maxPortieri")
	private Integer maxPortieri;
	
	@Column(name="maxDifensori")
	private Integer maxDifensori;
	
	@Column(name="maxCentroCampisti")
	private Integer maxCentroCamposti;
	
	@Column(name="maxAttaccanti")
	private Integer maxAttaccanti;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="invitationCode")
	private String invitationCode;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private UserLogin owner;
	
	public Gruppo() {
		super();
		maxPortieri = 2;
		maxDifensori = 6;
		maxCentroCamposti = 6;
		maxAttaccanti = 4;
		maxCredito = 200;
	}

	public Gruppo(Integer idGruppo, Date dateCreazione, Integer maxCredito,
			String nome,UserLogin owner) {
		super();
		this.idGruppo = idGruppo;
		this.dataCreazione = dateCreazione;
		this.maxCredito = maxCredito;
		this.nome = nome;
		this.owner = owner;
	}

	public Gruppo(Integer idGruppo, Date dateCreazione, Integer maxCredito,
			Integer maxPortieri, Integer maxDifensori,
			Integer maxCentroCamposti, Integer maxAttaccanti, String nome) {
		super();
		this.idGruppo = idGruppo;
		this.dataCreazione = dateCreazione;
		this.maxCredito = maxCredito;
		this.maxPortieri = maxPortieri;
		this.maxDifensori = maxDifensori;
		this.maxCentroCamposti = maxCentroCamposti;
		this.maxAttaccanti = maxAttaccanti;
		this.nome = nome;
	}
	public Gruppo (UserLogin owner)
	{
		this.owner = owner;
	}

	public Gruppo(String groupName) {
		this.nome = groupName;
	}

	/**
	 * @return the idGruppo
	 */
	public Integer getIdGruppo() {
		return idGruppo;
	}

	/**
	 * @param idGruppo the idGruppo to set
	 */
	public void setIdGruppo(Integer idGruppo) {
		this.idGruppo = idGruppo;
	}

	/**
	 * @return the dateCreazione
	 */
	public Date getDateCreazione() {
		return dataCreazione;
	}

	/**
	 * @param dateCreazione the dateCreazione to set
	 */
	public void setDateCreazione(Date dateCreazione) {
		this.dataCreazione = dateCreazione;
	}

	/**
	 * @return the maxCredito
	 */
	public Integer getMaxCredito() {
		return maxCredito;
	}

	/**
	 * @param maxCredito the maxCredito to set
	 */
	public void setMaxCredito(Integer maxCredito) {
		this.maxCredito = maxCredito;
	}

	/**
	 * @return the maxPortieri
	 */
	public Integer getMaxPortieri() {
		return maxPortieri;
	}

	/**
	 * @param maxPortieri the maxPortieri to set
	 */
	public void setMaxPortieri(Integer maxPortieri) {
		this.maxPortieri = maxPortieri;
	}

	/**
	 * @return the maxDifensori
	 */
	public Integer getMaxDifensori() {
		return maxDifensori;
	}

	/**
	 * @param maxDifensori the maxDifensori to set
	 */
	public void setMaxDifensori(Integer maxDifensori) {
		this.maxDifensori = maxDifensori;
	}

	/**
	 * @return the maxCentroCamposti
	 */
	public Integer getMaxCentroCamposti() {
		return maxCentroCamposti;
	}

	/**
	 * @param maxCentroCamposti the maxCentroCamposti to set
	 */
	public void setMaxCentroCamposti(Integer maxCentroCamposti) {
		this.maxCentroCamposti = maxCentroCamposti;
	}

	/**
	 * @return the maxAttaccanti
	 */
	public Integer getMaxAttaccanti() {
		return maxAttaccanti;
	}

	/**
	 * @param maxAttaccanti the maxAttaccanti to set
	 */
	public void setMaxAttaccanti(Integer maxAttaccanti) {
		this.maxAttaccanti = maxAttaccanti;
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
	 * @return the owner
	 */
	public UserLogin getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(UserLogin owner) {
		this.owner = owner;
	}

	
	
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Gruppo [idGruppo=" + idGruppo + ", dataCreazione="
				+ dataCreazione + ", maxCredito=" + maxCredito
				+ ", maxPortieri=" + maxPortieri + ", maxDifensori="
				+ maxDifensori + ", maxCentroCamposti=" + maxCentroCamposti
				+ ", maxAttaccanti=" + maxAttaccanti + ", nome=" + nome
				+ ", owner=" + owner + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCreazione == null) ? 0 : dataCreazione.hashCode());
		result = prime * result
				+ ((idGruppo == null) ? 0 : idGruppo.hashCode());
		result = prime * result
				+ ((maxAttaccanti == null) ? 0 : maxAttaccanti.hashCode());
		result = prime
				* result
				+ ((maxCentroCamposti == null) ? 0 : maxCentroCamposti
						.hashCode());
		result = prime * result
				+ ((maxCredito == null) ? 0 : maxCredito.hashCode());
		result = prime * result
				+ ((maxDifensori == null) ? 0 : maxDifensori.hashCode());
		result = prime * result
				+ ((maxPortieri == null) ? 0 : maxPortieri.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Gruppo other = (Gruppo) obj;
		if (dataCreazione == null) {
			if (other.dataCreazione != null)
				return false;
		} else if (!dataCreazione.equals(other.dataCreazione))
			return false;
		if (idGruppo == null) {
			if (other.idGruppo != null)
				return false;
		} else if (!idGruppo.equals(other.idGruppo))
			return false;
		if (maxAttaccanti == null) {
			if (other.maxAttaccanti != null)
				return false;
		} else if (!maxAttaccanti.equals(other.maxAttaccanti))
			return false;
		if (maxCentroCamposti == null) {
			if (other.maxCentroCamposti != null)
				return false;
		} else if (!maxCentroCamposti.equals(other.maxCentroCamposti))
			return false;
		if (maxCredito == null) {
			if (other.maxCredito != null)
				return false;
		} else if (!maxCredito.equals(other.maxCredito))
			return false;
		if (maxDifensori == null) {
			if (other.maxDifensori != null)
				return false;
		} else if (!maxDifensori.equals(other.maxDifensori))
			return false;
		if (maxPortieri == null) {
			if (other.maxPortieri != null)
				return false;
		} else if (!maxPortieri.equals(other.maxPortieri))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return idGruppo;
	}
	
	
	
	
}
