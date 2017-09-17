package com.fantagame.be.business.data.bean.squadra.rosa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.data.bean.Iface.IBean;


@Entity
@Table(name="ruolo")
@XmlRootElement(name = "Ruolo")
public class Ruolo implements Serializable,IBean<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4491436497452071L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idRuolo" ,nullable=false,unique = true)
	private Integer idRuolo;
	
	@Column(name="descrizione",nullable=false)
	private String desc;
	
	public Ruolo() {
		super();
	}
	public Ruolo(Integer idRuolo, String desc) {
		super();
		this.idRuolo = idRuolo;
		this.desc = desc;
	}
	/**
	 * @return the idRuolo
	 */
	public Integer getIdRuolo() {
		return idRuolo;
	}
	/**
	 * @param idRuolo the idRuolo to set
	 */
	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ruolo [idRuolo=" + idRuolo + ", desc=" + desc + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((idRuolo == null) ? 0 : idRuolo.hashCode());
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
		Ruolo other = (Ruolo) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (idRuolo == null) {
			if (other.idRuolo != null)
				return false;
		} else if (!idRuolo.equals(other.idRuolo))
			return false;
		return true;
	}
	@Override
	public Integer getId() {
		return idRuolo;
	}
	
	
}
