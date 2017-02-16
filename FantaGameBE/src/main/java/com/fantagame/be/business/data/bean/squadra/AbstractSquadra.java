package com.fantagame.be.business.data.bean.squadra;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fantagame.be.business.data.bean.Iface.IBean;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractSquadra implements Serializable,IBean<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5422811420538147564L;

	@Id
	@PrimaryKeyJoinColumn
	@Column(name="idSquadra",nullable=false,unique = true)
	private Integer idSquadra;
	
	@Column(name="nome",nullable=false)
	private String nome;
	
	public AbstractSquadra() {
		super();
	}

	public AbstractSquadra(Integer idSquadra, String nome) {
		super();
		this.idSquadra = idSquadra;
		this.nome = nome;
	}

	/**
	 * @return the idSquadra
	 */
	public Integer getIdSquadra() {
		return idSquadra;
	}

	/**
	 * @param idSquadra the idSquadra to set
	 */
	public void setIdSquadra(Integer idSquadra) {
		this.idSquadra = idSquadra;
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Squadra [idSquadra=" + idSquadra + ", nome=" + nome + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSquadra == null) ? 0 : idSquadra.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		AbstractSquadra other = (AbstractSquadra) obj;
		if (idSquadra == null) {
			if (other.idSquadra != null)
				return false;
		} else if (!idSquadra.equals(other.idSquadra))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return idSquadra;
	}
}
