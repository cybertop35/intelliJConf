package com.fantagame.be.business.data.bean.squadra.rosa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

 
import com.fantagame.be.business.data.bean.Andamento;
import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.business.data.bean.squadra.RealSquadra;


@Entity
@Table(name="giocatore")
@XmlRootElement(name = "Giocatore")
public class Giocatore implements Serializable,IBean<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859559092843551041L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idGiocatore")
	private Integer idGiocatore;

	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="eta")
	private Integer eta;
	
	@Column(name="quotazione")
	private Integer quotazione;
	
	@OneToOne
	@JoinColumn(name="idRuolo")
	private Ruolo ruolo;
	
	@ManyToOne
	@JoinColumn(name="idSquadra")
	private RealSquadra realSquadra;
	
	@OneToMany
	@JoinColumn(name="idgiocatore")
	private List<Andamento> andamenti; 
	
	public Giocatore() {
		super();
	}

	public Giocatore(Integer idGiocatore, String nome, String cognome,
			Integer quotazione,Ruolo ruolo,RealSquadra squadra) {
		super();
		this.idGiocatore = idGiocatore;
		this.nome = nome;
		this.cognome = cognome;
		this.quotazione = quotazione;
		this.ruolo = ruolo;
		this.realSquadra = squadra;
	}

	public Giocatore(Integer idGiocatore, String nome, String cognome,
			Integer eta, Integer quotazione,Ruolo ruolo,RealSquadra squadra) {
		super();
		this.idGiocatore = idGiocatore;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.quotazione = quotazione;
		this.ruolo = ruolo;
		this.realSquadra = squadra;
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

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the eta
	 */
	public Integer getEta() {
		return eta;
	}

	/**
	 * @param eta the eta to set
	 */
	public void setEta(Integer eta) {
		this.eta = eta;
	}

	/**
	 * @return the quotazione
	 */
	public Integer getQuotazione() {
		return quotazione;
	}

	/**
	 * @param quotazione the quotazione to set
	 */
	public void setQuotazione(Integer quotazione) {
		this.quotazione = quotazione;
	}
	
	
	

	/**
	 * @return the ruolo
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the realSquadra
	 */
	public RealSquadra getRealSquadra() {
		return realSquadra;
	}

	/**
	 * @param realSquadra the realSquadra to set
	 */
	public void setRealSquadra(RealSquadra realSquadra) {
		this.realSquadra = realSquadra;
	}

	/**
	 * @return the andamenti
	 */
	public List<Andamento> getAndamenti() {
		return andamenti;
	}

	/**
	 * @param andamenti the andamenti to set
	 */
	public void setAndamenti(List<Andamento> andamenti) {
		this.andamenti = andamenti;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Giocatore [idGiocatore=" + idGiocatore + ", nome=" + nome
				+ ", cognome=" + cognome + ", eta=" + eta + ", quotazione="
				+ quotazione + ", squadre=" +  ", ruolo=" + ruolo
				+ ", realSquadra=" + realSquadra + ", andamenti=" + andamenti
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((andamenti == null) ? 0 : andamenti.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((eta == null) ? 0 : eta.hashCode());
		result = prime * result
				+ ((idGiocatore == null) ? 0 : idGiocatore.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((quotazione == null) ? 0 : quotazione.hashCode());
		result = prime * result
				+ ((realSquadra == null) ? 0 : realSquadra.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
	
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
		Giocatore other = (Giocatore) obj;
		if (andamenti == null) {
			if (other.andamenti != null)
				return false;
		} else if (!andamenti.equals(other.andamenti))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (eta == null) {
			if (other.eta != null)
				return false;
		} else if (!eta.equals(other.eta))
			return false;
		if (idGiocatore == null) {
			if (other.idGiocatore != null)
				return false;
		} else if (!idGiocatore.equals(other.idGiocatore))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quotazione == null) {
			if (other.quotazione != null)
				return false;
		} else if (!quotazione.equals(other.quotazione))
			return false;
		if (realSquadra == null) {
			if (other.realSquadra != null)
				return false;
		} else if (!realSquadra.equals(other.realSquadra))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		
		return true;
	}

	@Override
	public Integer getId() {
		return idGiocatore;
	}
	
	
}
