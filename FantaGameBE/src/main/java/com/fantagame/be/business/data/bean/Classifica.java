package com.fantagame.be.business.data.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.bean.complexId.ClassificaAssociationId;
import com.fantagame.be.business.data.bean.squadra.FantaSquadra;

@Entity
@Table(name="Classifica")
@IdClass(ClassificaAssociationId.class)
@XmlRootElement(name = "Classifica")
public class Classifica implements Serializable,IBean<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552902345748790060L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idClassifica")
	private Integer idClassifica;
	
	@Id
	@Column(name="data")
	private Date data;
	
	@Column(name="punti")
	private Double punti;
	
	@ManyToOne
	@JoinColumn(name="idSquadra")
	private FantaSquadra fantaSquadra;
	
	public Classifica() {
		super();
	}

	public Classifica(Integer idClassifica, Date date,FantaSquadra fantaSquadra, Double punti) {
		super();
		this.idClassifica = idClassifica;
		this.data = date;
		this.fantaSquadra = fantaSquadra;
		this.punti = punti;
	}

	/**
	 * @return the idClassifica
	 */
	public Integer getIdClassifica() {
		return idClassifica;
	}

	/**
	 * @param idClassifica the idClassifica to set
	 */
	public void setIdClassifica(Integer idClassifica) {
		this.idClassifica = idClassifica;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return data;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.data = date;
	}

	/**
	 * @return the punti
	 */
	public Double getPunti() {
		return punti;
	}

	/**
	 * @param punti the punti to set
	 */
	public void setPunti(Double punti) {
		this.punti = punti;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Classifica [idClassifica=" + idClassifica + ", date=" + data
				+ ", punti=" + punti + ", fantaSquadra=" + fantaSquadra + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((fantaSquadra == null) ? 0 : fantaSquadra.hashCode());
		result = prime * result
				+ ((idClassifica == null) ? 0 : idClassifica.hashCode());
		result = prime * result + ((punti == null) ? 0 : punti.hashCode());
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
		Classifica other = (Classifica) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (fantaSquadra == null) {
			if (other.fantaSquadra != null)
				return false;
		} else if (!fantaSquadra.equals(other.fantaSquadra))
			return false;
		if (idClassifica == null) {
			if (other.idClassifica != null)
				return false;
		} else if (!idClassifica.equals(other.idClassifica))
			return false;
		if (punti == null) {
			if (other.punti != null)
				return false;
		} else if (!punti.equals(other.punti))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
 
		return idClassifica;
	}
	
	
	
	
}
