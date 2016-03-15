package com.fantagame.be.business.data.bean.complexId;

import java.io.Serializable;
import java.util.Date;

public class AndamentoAssociationId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157600582735390327L;

//	@Id	
	private Integer idGiocatore;
	

//	@Column(name="data",nullable= false)
	private Date data;
	
	public AndamentoAssociationId() {
		super();
		 
	}
	
	public AndamentoAssociationId(Integer idGiocatore,Date data) {
		super();
		this.idGiocatore = idGiocatore;
		this.data = data;
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
	
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GiocatoreAssociationId [idGiocatore=" + idGiocatore + "]";
	}
	
	

	public int hashCode() {
	    return (int)( idGiocatore);
	  }
	 
	  public boolean equals(Object object) {
	    if (object instanceof AndamentoAssociationId) {
	    	AndamentoAssociationId otherId = (AndamentoAssociationId) object;
	      return (otherId.idGiocatore == this.idGiocatore);
	    }
	    return false;
	  }
}
