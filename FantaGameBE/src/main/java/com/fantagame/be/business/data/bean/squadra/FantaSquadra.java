package com.fantagame.be.business.data.bean.squadra;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fantagame.be.business.data.bean.Classifica;
import com.fantagame.be.business.data.bean.Gruppo;
import com.fantagame.be.business.data.bean.UserLogin;
import com.fantagame.be.business.data.bean.squadra.Iface.Rosa;
import com.fantagame.be.business.data.bean.squadra.rosa.Formazione;

@Entity
@Table(name="fantaSquadra")
@XmlRootElement(name = "FantaSquadra")
public class FantaSquadra extends AbstractSquadra implements Rosa <Formazione>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7973035430182700284L;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="userNick")
	@JsonIgnore
	private UserLogin userNick;
	
	@OneToOne
	@JoinColumn(name="idGruppo")
	private Gruppo gruppo;
	
	@OneToMany(mappedBy="fantaSquadra", cascade = CascadeType.ALL)	
	private List<Classifica> classifica;
	
	@OneToMany(mappedBy ="fantaSquadra", cascade = CascadeType.ALL)
	private List<Formazione> rosa;
	
	public FantaSquadra() {
		super();
		 
	}
	public FantaSquadra(Integer idSquadra, String nome) {
		super(idSquadra, nome);
	}
	
	public FantaSquadra(UserLogin userLogin, Gruppo gruppo) {
		super();
		this.userNick = userLogin;
		this.gruppo = gruppo;
	}
	/**
	 * @return the persona
	 */
	@JsonIgnore
	public UserLogin getUserLogin() {
		return userNick;
	}
	/**
	 * @param persona the persona to set
	 */
	public void setUserLogin(UserLogin userLogin) {
		this.userNick = userLogin;
	}
	/**
	 * @return the gruppo
	 */
	public Gruppo getGruppo() {
		return gruppo;
	}
	/**
	 * @param gruppo the gruppo to set
	 */
	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}
	
	/**
	 * @return the classifica
	 */
	public List<Classifica> getClassifica() {
		return classifica;
	}
	/**
	 * @param classifica the classifica to set
	 */
	public void setClassifica(List<Classifica> classifica) {
		this.classifica = classifica;
	}
	
	/**
	 * @return the rosa
	 */
	public List<Formazione> getRosa() {
		return rosa;
	}
	/**
	 * @param rosa the rosa to set
	 */
	public void setRosa(List<Formazione> rosa) {
		this.rosa = rosa;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FantaSquadra [persona=" + userNick + ", gruppo=" + gruppo
				+ ", punteggi=" + classifica + ", rosa=" + rosa + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((classifica == null) ? 0 : classifica.hashCode());
		result = prime * result + ((gruppo == null) ? 0 : gruppo.hashCode());
		result = prime * result + ((userNick == null) ? 0 : userNick.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FantaSquadra other = (FantaSquadra) obj;
		if (classifica == null) {
			if (other.classifica != null)
				return false;
		} else if (!classifica.equals(other.classifica))
			return false;
		if (gruppo == null) {
			if (other.gruppo != null)
				return false;
		} else if (!gruppo.equals(other.gruppo))
			return false;
		if (userNick == null) {
			if (other.userNick != null)
				return false;
		} else if (!userNick.equals(other.userNick))
			return false;
		return true;
	}
	
	
	
}
