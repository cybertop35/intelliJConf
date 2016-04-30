package com.fantagame.be.business.data.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.bean.complexId.CalendarioId;
import com.fantagame.be.business.data.bean.squadra.RealSquadra;


@Entity
@Table(name="Calendario")
@IdClass(CalendarioId.class)
@XmlRootElement(name = "Calendario")
public class Calendario implements Serializable,IBean<CalendarioId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3581809869668591926L;

	
	@OneToOne 
	@JoinColumn(name="idSquadraCasa",insertable=false,updatable=false,referencedColumnName="idSquadra")
	private RealSquadra squadraCasa;
	
	@OneToOne 
	@JoinColumn(name="idSquadraOspite",insertable=false,updatable=false,referencedColumnName="idSquadra")
	private RealSquadra squadraOspite;	
	
	@Id
	@Column(name = "idSquadraCasa")
	private Integer idSquadraCasa;
	
	@Id
	@Column(name = "idSquadraOspite")
	private Integer idSquadraOspiti;
	
	
	@Column(name="Giornata")
	private Integer giornata;
	
	
	@Column(name="Data")
	private Date date;
	
	@Column(name="RisultatoA")
	private Integer risultatoA;
	
	@Column(name="RisultatoB")
	private Integer risultatoB;
	
	@Column(name="Campionato")
	private String campionato;
	
	@Column(name="Categoria")
	private String categoria;
	

	public Calendario() {
		super();
		
	}

	public Calendario(RealSquadra realSquadraCasa,RealSquadra realSquadraOspite,
			Integer giornata, Date date, String campionato,	String categoria) 
	{
		super();

		this.date = date;
		this.squadraCasa = realSquadraCasa;
		this.squadraOspite = realSquadraOspite;
		this.giornata = giornata;
		this.campionato = campionato;
		this.categoria = categoria;
		idSquadraCasa = realSquadraCasa.getId();
		idSquadraOspiti = realSquadraOspite.getId();

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

	public RealSquadra getSquadraCasa() {
		return squadraCasa;
	}

	public void setSquadraCasa(RealSquadra squadraCasa) {
		this.squadraCasa = squadraCasa;
	}

	public RealSquadra getSquadraOspite() {
		return squadraOspite;
	}

	public void setSquadraOspite(RealSquadra squadraOspite) {
		this.squadraOspite = squadraOspite;
	}

	public Integer getGiornata() {
		return giornata;
	}

	public void setGiornata(Integer giornata) {
		this.giornata = giornata;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public CalendarioId getId() {
		return null;
	}
		
	public Integer getRisultatoA() {
		return risultatoA;
	}

	public void setRisultatoA(Integer risultatoA) {
		this.risultatoA = risultatoA;
	}

	public Integer getRisultatoB() {
		return risultatoB;
	}

	public void setRisultatoB(Integer risultatoB) {
		this.risultatoB = risultatoB;
	}

	/**
	 * @return the campionato
	 */
	public String getCampionato() {
		return campionato;
	}

	/**
	 * @param campionato the campionato to set
	 */
	public void setCampionato(String campionato) {
		this.campionato = campionato;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	


	@Override
	public String toString() {
		return "Calendario [squadraCasa=" + squadraCasa + ", squadraOspite="
				+ squadraOspite + ", idSquadraCasa=" + idSquadraCasa
				+ ", idSquadraOspiti=" + idSquadraOspiti + ", giornata="
				+ giornata + ", date=" + date + ", risultatoA=" + risultatoA
				+ ", risultatoB=" + risultatoB + ", campionato=" + campionato
				+ ", categoria=" + categoria + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campionato == null) ? 0 : campionato.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((giornata == null) ? 0 : giornata.hashCode());
		result = prime * result
				+ ((idSquadraCasa == null) ? 0 : idSquadraCasa.hashCode());
		result = prime * result
				+ ((idSquadraOspiti == null) ? 0 : idSquadraOspiti.hashCode());
		result = prime * result
				+ ((risultatoA == null) ? 0 : risultatoA.hashCode());
		result = prime * result
				+ ((risultatoB == null) ? 0 : risultatoB.hashCode());
		result = prime * result
				+ ((squadraCasa == null) ? 0 : squadraCasa.hashCode());
		result = prime * result
				+ ((squadraOspite == null) ? 0 : squadraOspite.hashCode());
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
		Calendario other = (Calendario) obj;
		if (campionato == null) {
			if (other.campionato != null)
				return false;
		} else if (!campionato.equals(other.campionato))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (giornata == null) {
			if (other.giornata != null)
				return false;
		} else if (!giornata.equals(other.giornata))
			return false;
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
		if (risultatoA == null) {
			if (other.risultatoA != null)
				return false;
		} else if (!risultatoA.equals(other.risultatoA))
			return false;
		if (risultatoB == null) {
			if (other.risultatoB != null)
				return false;
		} else if (!risultatoB.equals(other.risultatoB))
			return false;
		if (squadraCasa == null) {
			if (other.squadraCasa != null)
				return false;
		} else if (!squadraCasa.equals(other.squadraCasa))
			return false;
		if (squadraOspite == null) {
			if (other.squadraOspite != null)
				return false;
		} else if (!squadraOspite.equals(other.squadraOspite))
			return false;
		return true;
	}

	
	
	
}
