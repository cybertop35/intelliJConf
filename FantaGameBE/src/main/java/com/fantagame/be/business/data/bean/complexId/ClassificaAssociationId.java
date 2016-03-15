package com.fantagame.be.business.data.bean.complexId;

import java.io.Serializable;
import java.util.Date;

public class ClassificaAssociationId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256783799421648594L;

	private Integer idClassifica;
	private Date data;
	
	public ClassificaAssociationId(){}
	
	public ClassificaAssociationId(Integer idClassifica, Date data) {
		super();
		this.idClassifica = idClassifica;
		this.data = data;
	}
	
	public Integer getIdClassifica() {
		return idClassifica;
	}
	
	public void setIdClassifica(Integer idClassifica) {
		this.idClassifica = idClassifica;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ClassificaAssociationId [idClassifica=" + idClassifica
				+ ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((idClassifica == null) ? 0 : idClassifica.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassificaAssociationId other = (ClassificaAssociationId) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!(data.compareTo(other.data) == 0))
			return false;
		if (idClassifica == null) {
			if (other.idClassifica != null)
				return false;
		} else if (!idClassifica.equals(other.idClassifica))
			return false;
		return true;
	}

	


	
}
