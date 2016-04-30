package com.fantagame.be.business.data.bean.squadra.rosa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;


import com.fantagame.be.business.data.bean.squadra.FantaSquadra;
import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.bean.complexId.GiocatoreAssociationId;

  

@Entity
@Table(name="formazione")
@IdClass(GiocatoreAssociationId.class)
@XmlRootElement(name = "Formazione")
public class Formazione implements Serializable,IBean<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5990586653654167822L;

	@Id
	@Column(name="idSquadra")
	private Integer idFantaSquadra;
	
	@Id
	@Column(name="idGiocatore")
	private Integer idGiocatore;
	
	@Column(name="titolare")
	private Boolean titolare;
	
	@Column(name="panchina")
	private Integer panchina;
	
	@ManyToOne
	@JoinColumn(name="idSquadra",updatable=false,insertable=false)
	@PrimaryKeyJoinColumn(name="idSquadra", referencedColumnName="idSquadra")
	@JsonIgnore
	private FantaSquadra fantaSquadra;
	
	@ManyToOne
	@JoinColumn(name="idGiocatore",updatable=false,insertable=false)
	@PrimaryKeyJoinColumn(name="idGiocatore", referencedColumnName="idGiocatore")
	private Giocatore giocatore;
	
	
	public Formazione() {
		super();
		
	}

	public Formazione(Boolean titolare, Integer panchina) {
		super();
		this.titolare = titolare;
		this.panchina = panchina;
	}

	
	public Formazione(Integer idFantaSquadra, Integer idGiocatore,
			Boolean titolare, Integer panchina, FantaSquadra fantaSquadra,
			Giocatore giocatore) {
		super();
		this.idFantaSquadra = idFantaSquadra;
		this.idGiocatore = idGiocatore;
		this.titolare = titolare;
		this.panchina = panchina;
		this.fantaSquadra = fantaSquadra;
		this.giocatore = giocatore;
	}

	/**
	 * @return the titolare
	 */
	public Boolean getTitolare() {
		return titolare;
	}

	/**
	 * @param titolare the titolare to set
	 */
	public void setTitolare(Boolean titolare) {
		this.titolare = titolare;
	}

	/**
	 * @return the panchina
	 */
	public Integer getPanchina() {
		return panchina;
	}

	/**
	 * @param panchina the panchina to set
	 */
	public void setPanchina(Integer panchina) {
		this.panchina = panchina;
	}

	/**
	 * @return the idFantaSquadra
	 */
	public Integer getIdFantaSquadra() {
		return idFantaSquadra;
	}

	/**
	 * @param idFantaSquadra the idFantaSquadra to set
	 */
	public void setIdFantaSquadra(Integer idFantaSquadra) {
		this.idFantaSquadra = idFantaSquadra;
	}

	/**
	 * @return the idGiocatore
	 */
	public Integer getIdGiocatore() {
		return idGiocatore;
	}

	/**
	 * @param idGiocatore the idGiocatore to set
	 */
	public void setIdGiocatore(Integer idGiocatore) {
		this.idGiocatore = idGiocatore;
	}

	/**
	 * @return the fantaSquadra
	 */
	public FantaSquadra getFantaSquadra() {
		return fantaSquadra;
	}

	/**
	 * @param fantaSquadra the fantaSquadra to set
	 */
	public void setFantaSquadra(FantaSquadra fantaSquadra) {
		this.fantaSquadra = fantaSquadra;
	}

	/**
	 * @return the giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * @param giocatore the giocatore to set
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Formazione [idFantaSquadra=" + idFantaSquadra
				+ ", idGiocatore=" + idGiocatore + ", titolare=" + titolare
				+ ", panchina=" + panchina + ", fantaSquadra=" + fantaSquadra
				+ ", giocatore=" + giocatore + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((panchina == null) ? 0 : panchina.hashCode());
		result = prime * result
				+ ((titolare == null) ? 0 : titolare.hashCode());
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
		Formazione other = (Formazione) obj;
		if (panchina == null) {
			if (other.panchina != null)
				return false;
		} else if (!panchina.equals(other.panchina))
			return false;
		if (titolare == null) {
			if (other.titolare != null)
				return false;
		} else if (!titolare.equals(other.titolare))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return idFantaSquadra;
	}

	
	
}
