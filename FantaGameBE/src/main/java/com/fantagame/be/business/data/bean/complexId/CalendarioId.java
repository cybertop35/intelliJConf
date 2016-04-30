package com.fantagame.be.business.data.bean.complexId;



import java.io.Serializable;

public class CalendarioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8704367941309267946L;
	


	private Integer idSquadraCasa;
	

	private Integer idSquadraOspiti;
	
//	private Integer giornata;
//	
//	private Date date;
	
	public CalendarioId() {
		super();
		 
	}

	public CalendarioId(Integer realSquadraCasa, Integer realSquadraOspite){//,Integer giornata, Date date) {
		super();
		this.idSquadraCasa = realSquadraCasa;
		this.idSquadraOspiti = realSquadraOspite;
//		this.giornata = giornata;
//		this.date = date;
	}

	



	public Integer getIdSquadraCasa() {
		return idSquadraCasa;
	}

	public void setIdSquadraCasa(Integer idSquadraCasa) {
		this.idSquadraCasa = idSquadraCasa;
	}

	public Integer getIdSquadraOspiti() {
		return idSquadraOspiti;
	}

	public void setIdSquadraOspiti(Integer idSquadraOspiti) {
		this.idSquadraOspiti = idSquadraOspiti;
	}

//	public Integer getGiornata() {
//		return giornata;
//	}
//
//	public void setGiornata(Integer giornata) {
//		this.giornata = giornata;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

	@Override
	public String toString() {
		return "CalendarioId [idSquadraCasa=" + idSquadraCasa
				+ ", idSquadraOspiti=" + idSquadraOspiti + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSquadraCasa == null) ? 0 : idSquadraCasa.hashCode());
		result = prime * result
				+ ((idSquadraOspiti == null) ? 0 : idSquadraOspiti.hashCode());
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
		CalendarioId other = (CalendarioId) obj;
		if (idSquadraCasa == null) {
			if (other.idSquadraCasa != null)
				return false;
		} else if (!idSquadraCasa.equals(other.idSquadraCasa))
			return false;
		if (idSquadraOspiti == null) {
			if (other.idSquadraOspiti != null)
				return false;
		} else if (!idSquadraOspiti.equals(other.idSquadraOspiti))
			return false;
		return true;
	}
	
}
