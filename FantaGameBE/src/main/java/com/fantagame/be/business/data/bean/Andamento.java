package com.fantagame.be.business.data.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.bean.complexId.AndamentoAssociationId;

@Entity
@Table(name="Andamento")
@IdClass(AndamentoAssociationId.class)
@XmlRootElement(name = "Andamento")
public class Andamento implements Serializable,IBean<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6853690700888867799L;

	@Id	
	private Integer idGiocatore;
	
	@Id
	@Column(name="data",nullable= false)
	private Date data;
	
	private Integer giornata;
	
	@Column(name="VotoG")
	private Double votoG;
	
	@Column(name="VotoCDS")
	private Double votoCDS;
	
	@Column(name="FVG")
	private Double FVG;
	
	@Column(name="FVCDS")
	private Double FVCDS;
	
	@Column(name="MediaVotoG")
	private Double mediaVotoG;
	
	@Column(name="MediaVotoCDS")
	private Double mediaVotoCDS;
	
	@Column(name="MediaVotoFVG")
	private Double mediaVotoFVG;
	
	@Column(name="MediaVotoFVCDS")
	private Double mediaVotoFVCDS;
	
	@Column(name="GolSub")
	private Integer golSubiti;
	
	@Column(name="GolFat")
	private Integer golFatti;
	
	@Column(name="Amm")
	private boolean ammonito;
	
	@Column(name="Esp")
	private boolean espulso;
	
	@Column(name="titolare")
	private boolean titolare;
	
	@Column(name="noVoto" )
	private boolean noVoto;
	
	public Andamento() {
		super();
	
	}

	public Andamento(Integer idGiocatore,Date date, Double mediaVoto, Double voto) {
		super();
		this.idGiocatore = idGiocatore;
		this.data = date;
		this.mediaVotoG = mediaVoto;
		this.votoG = voto;
	}

	
	@Override
	public Integer getId() {
		return idGiocatore;
	}

	public Integer getIdGiocatore() {
		return idGiocatore;
	}

	public void setIdGiocatore(Integer idGiocatore) {
		this.idGiocatore = idGiocatore;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getGiornata() {
		return giornata;
	}

	public void setGiornata(Integer giornata) {
		this.giornata = giornata;
	}

	public Double getVotoG() {
		return votoG;
	}

	public void setVotoG(Double votoG) {
		this.votoG = votoG;
	}

	public Double getVotoCDS() {
		return votoCDS;
	}

	public void setVotoCDS(Double votoCDS) {
		this.votoCDS = votoCDS;
	}

	public Double getFVG() {
		return FVG;
	}

	public void setFVG(Double fVG) {
		FVG = fVG;
	}

	public Double getFVCDS() {
		return FVCDS;
	}

	public void setFVCDS(Double fVCDS) {
		FVCDS = fVCDS;
	}

	public Double getMediaVotoG() {
		return mediaVotoG;
	}

	public void setMediaVotoG(Double mediaVoto) {
		this.mediaVotoG = mediaVoto;
	}
	
	public Double getMediaVotoCDS() {
		return mediaVotoCDS;
	}

	public void setMediaVotoCDS(Double mediaVotoCDS) {
		this.mediaVotoCDS = mediaVotoCDS;
	}

	public Double getMediaVotoFVG() {
		return mediaVotoFVG;
	}

	public void setMediaVotoFVG(Double mediaVotoFVG) {
		this.mediaVotoFVG = mediaVotoFVG;
	}

	public Double getMediaVotoFVCDS() {
		return mediaVotoFVCDS;
	}

	public void setMediaVotoFVCDS(Double mediaVotoFVCDS) {
		this.mediaVotoFVCDS = mediaVotoFVCDS;
	}

	public Integer getGolSubiti() {
		return golSubiti;
	}

	public void setGolSubiti(Integer golSubiti) {
		this.golSubiti = golSubiti;
	}

	public Integer getGolFatti() {
		return golFatti;
	}

	public void setGolFatti(Integer golFatti) {
		this.golFatti = golFatti;
	}

	public boolean isAmmonito() {
		return ammonito;
	}

	public void setAmmonito(boolean ammonito) {
		this.ammonito = ammonito;
	}

	public boolean isEspulso() {
		return espulso;
	}

	public void setEspulso(boolean espulso) {
		this.espulso = espulso;
	}

	public boolean isTitolare() {
		return titolare;
	}

	public void setTitolare(boolean titolare) {
		this.titolare = titolare;
	}	
	
	public boolean isNoVoto() {
		return noVoto;
	}

	public void setNoVoto(boolean noVoto) {
		this.noVoto = noVoto;
	}

	@Override
	public String toString() {
		return "Andamento [idGiocatore=" + idGiocatore + ", data=" + data
				+ ", giornata=" + giornata + ", votoG=" + votoG + ", votoCDS="
				+ votoCDS + ", FVG=" + FVG + ", FVCDS=" + FVCDS
				+ ", mediaVotoG=" + mediaVotoG + ", mediaVotoCDS="
				+ mediaVotoCDS + ", mediaVotoFVG=" + mediaVotoFVG
				+ ", mediaVotoFVCDS=" + mediaVotoFVCDS + ", golSubiti="
				+ golSubiti + ", golFatti=" + golFatti + ", ammonito="
				+ ammonito + ", espulso=" + espulso + ", titolare=" + titolare
				+ ", noVoto=" + noVoto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FVCDS == null) ? 0 : FVCDS.hashCode());
		result = prime * result + ((FVG == null) ? 0 : FVG.hashCode());
		result = prime * result + (ammonito ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + (espulso ? 1231 : 1237);
		result = prime * result
				+ ((giornata == null) ? 0 : giornata.hashCode());
		result = prime * result
				+ ((golFatti == null) ? 0 : golFatti.hashCode());
		result = prime * result
				+ ((golSubiti == null) ? 0 : golSubiti.hashCode());
		result = prime * result
				+ ((idGiocatore == null) ? 0 : idGiocatore.hashCode());
		result = prime * result
				+ ((mediaVotoCDS == null) ? 0 : mediaVotoCDS.hashCode());
		result = prime * result
				+ ((mediaVotoFVCDS == null) ? 0 : mediaVotoFVCDS.hashCode());
		result = prime * result
				+ ((mediaVotoFVG == null) ? 0 : mediaVotoFVG.hashCode());
		result = prime * result
				+ ((mediaVotoG == null) ? 0 : mediaVotoG.hashCode());
		result = prime * result + (noVoto ? 1231 : 1237);
		result = prime * result + (titolare ? 1231 : 1237);
		result = prime * result + ((votoCDS == null) ? 0 : votoCDS.hashCode());
		result = prime * result + ((votoG == null) ? 0 : votoG.hashCode());
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
		Andamento other = (Andamento) obj;
		if (FVCDS == null) {
			if (other.FVCDS != null)
				return false;
		} else if (!FVCDS.equals(other.FVCDS))
			return false;
		if (FVG == null) {
			if (other.FVG != null)
				return false;
		} else if (!FVG.equals(other.FVG))
			return false;
		if (ammonito != other.ammonito)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (espulso != other.espulso)
			return false;
		if (giornata == null) {
			if (other.giornata != null)
				return false;
		} else if (!giornata.equals(other.giornata))
			return false;
		if (golFatti == null) {
			if (other.golFatti != null)
				return false;
		} else if (!golFatti.equals(other.golFatti))
			return false;
		if (golSubiti == null) {
			if (other.golSubiti != null)
				return false;
		} else if (!golSubiti.equals(other.golSubiti))
			return false;
		if (idGiocatore == null) {
			if (other.idGiocatore != null)
				return false;
		} else if (!idGiocatore.equals(other.idGiocatore))
			return false;
		if (mediaVotoCDS == null) {
			if (other.mediaVotoCDS != null)
				return false;
		} else if (!mediaVotoCDS.equals(other.mediaVotoCDS))
			return false;
		if (mediaVotoFVCDS == null) {
			if (other.mediaVotoFVCDS != null)
				return false;
		} else if (!mediaVotoFVCDS.equals(other.mediaVotoFVCDS))
			return false;
		if (mediaVotoFVG == null) {
			if (other.mediaVotoFVG != null)
				return false;
		} else if (!mediaVotoFVG.equals(other.mediaVotoFVG))
			return false;
		if (mediaVotoG == null) {
			if (other.mediaVotoG != null)
				return false;
		} else if (!mediaVotoG.equals(other.mediaVotoG))
			return false;
		if (noVoto != other.noVoto)
			return false;
		if (titolare != other.titolare)
			return false;
		if (votoCDS == null) {
			if (other.votoCDS != null)
				return false;
		} else if (!votoCDS.equals(other.votoCDS))
			return false;
		if (votoG == null) {
			if (other.votoG != null)
				return false;
		} else if (!votoG.equals(other.votoG))
			return false;
		return true;
	}
	
	
	
}
