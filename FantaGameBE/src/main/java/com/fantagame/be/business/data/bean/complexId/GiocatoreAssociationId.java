package com.fantagame.be.business.data.bean.complexId;



import java.io.Serializable;


public class GiocatoreAssociationId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3649413227121312964L;

	private Integer idFantaSquadra;
	private Integer idGiocatore;
	
	public GiocatoreAssociationId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GiocatoreAssociationId(Integer idFantaSquadra, Integer idGiocatore) {
		super();
		this.idFantaSquadra = idFantaSquadra;
		this.idGiocatore = idGiocatore;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GiocatoreAssociationId [idFantaSquadra=" + idFantaSquadra
				+ ", idGiocatore=" + idGiocatore + "]";
	}

	public int hashCode() {
	    return (int)(idFantaSquadra + idGiocatore);
	  }
	 
	  public boolean equals(Object object) {
	    if (object instanceof GiocatoreAssociationId) {
	    	GiocatoreAssociationId otherId = (GiocatoreAssociationId) object;
	      return (otherId.idFantaSquadra == this.idFantaSquadra) && (otherId.idGiocatore == this.idGiocatore);
	    }
	    return false;
	  }
}
