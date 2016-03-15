package com.fantagame.be.business.data.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fantagame.be.business.data.bean.Iface.IBean;
import com.fantagame.be.util.FileUtil;

@Entity
@Table(name="userlogin")
@XmlRootElement(name = "UserLogin")
public class UserLogin implements Serializable,IBean<String>,UserDetails,Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8723066251117383630L;

	@Id
	@Column(name="nick")
	private String nick;
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	@Column(name="attivo")
	@JsonIgnore
	private Boolean attivo;

    @OneToOne
    @JoinColumn(name="idUser")
    @JsonIgnore
    private Persona persona;
    
    @Column(name="activationCode")
    @JsonIgnore
    private String activationCode;
	
    @Lob
	@Column(name="Img")
    @JsonIgnore
	private byte[] img;
    
    @Column(name="isAccountNonExpired")
    @JsonIgnore
    private Boolean isAccountNonExpired;
    
    @Column(name="isAccountNonLocked")
    @JsonIgnore
    private Boolean isAccountNonLocked;
    
    @Column(name="isCredentialsNonExpired")
    @JsonIgnore
    private Boolean isCredentialsNonExpired;
    
    @OneToMany(mappedBy ="userLogin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Authorities> authorities;
    
//	@OneToMany(mappedBy="owner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<Gruppo> gruppi;
	
//	@OneToMany(mappedBy="userNick",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIgnore
//	private List<FantaSquadra> fantaSquadre;
	
    
	public UserLogin() {
		super();		
	}

	public UserLogin(String nick) {
		super();
		this.nick = nick;		
	}
	
	public UserLogin(String nick, String password, Boolean attivo,Persona persona,
					List< Authorities> authorities) {
		super();
		this.nick = nick;
		this.password = password;
		this.attivo = attivo;
		this.persona = persona;
		this.authorities = authorities;
		
	}

	public UserLogin(String nick, String password, Boolean attivo,Boolean isAccountNonLocked,
					Boolean isCredentialsNonExpired,Boolean isAccountNonExpired ,
					List< Authorities> authorities) {
		super();
		this.nick = nick;
		this.password = password;
		this.attivo = attivo;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.authorities = authorities;		
	}
	
	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the password
	 */
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the attivo
	 */
	@JsonIgnore
	public Boolean getAttivo() {
		return attivo;
	}

	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	/**
	 * @return the persona
	 */
	@JsonIgnore
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@JsonIgnore
	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * @return the img
	 */
	@JsonIgnore
	public byte[] getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(byte[] img) {
		this.img = img;
	}

	/**
	 * Returns the photo Input Stream.
	 * @return InputStream 
	 * @throws SQLException e
	 */
	@JsonIgnore
	public InputStream getPhotoContent() throws SQLException {
		if (getImg() == null) {
			return null;
		}
		
		return  new ByteArrayInputStream( getImg());
	}
	
	/**
	 * 
	 * @param sourceStream - Photo source input stream
	 * @throws IOException e
	 */
	@JsonIgnore
	public void setPhotoContent(InputStream in) throws IOException {
		setImg(FileUtil.loadBytesFromStreamForSize(in, in.available()));
	}
	
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List< Authorities> authorities) {
		this.authorities = authorities;
	}
	@JsonIgnore
	public List<Authorities> getAuthorities_() {
		return authorities;
	}


//	public List<Gruppo> getGruppi() {
//		return gruppi;
//	}
//
//	public void setGruppi(List<Gruppo> gruppi) {
//		this.gruppi = gruppi;
//	}

//	public List<FantaSquadra> getFantaSquadre() {
//		return fantaSquadre;
//	}
//
//	public void setFantaSquadre(List<FantaSquadra> fantaSquadre) {
//		this.fantaSquadre = fantaSquadre;
//	}

	@JsonIgnore
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		if(authorities == null)
			return null;
		return new ArrayList<GrantedAuthority>(authorities);
	}
	
	
	@Override
	@JsonIgnore
	public String getId() {
		return nick;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return nick;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return attivo;
	}
	
	@JsonIgnore
	@Override
	public String getName() {
		return nick;
	}

	@Override
	public String toString() {
		return "UserLogin [nick=" + nick + ", password=" + password
				+ ", attivo=" + attivo + ", persona=" + persona.getId()
				+ ", activationCode=" + activationCode
				+ ", isAccountNonExpired=" + isAccountNonExpired
				+ ", isAccountNonLocked=" + isAccountNonLocked
				+ ", isCredentialsNonExpired=" + isCredentialsNonExpired
				+ ", authorities=" + authorities + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((activationCode == null) ? 0 : activationCode.hashCode());
		result = prime * result + ((attivo == null) ? 0 : attivo.hashCode());
		result = prime * result	+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result	+ ((isAccountNonExpired == null) ? 0 : isAccountNonExpired.hashCode());
		result = prime * result	+ ((isAccountNonLocked == null) ? 0 : isAccountNonLocked.hashCode());
		result = prime * result	+ ((isCredentialsNonExpired == null) ? 0: isCredentialsNonExpired.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result	+ ((password == null) ? 0 : password.hashCode());
		//result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		UserLogin other = (UserLogin) obj;
		if (activationCode == null) {
			if (other.activationCode != null)
				return false;
		} else if (!activationCode.equals(other.activationCode))
			return false;
		if (attivo == null) {
			if (other.attivo != null)
				return false;
		} else if (!attivo.equals(other.attivo))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (isAccountNonExpired == null) {
			if (other.isAccountNonExpired != null)
				return false;
		} else if (!isAccountNonExpired.equals(other.isAccountNonExpired))
			return false;
		if (isAccountNonLocked == null) {
			if (other.isAccountNonLocked != null)
				return false;
		} else if (!isAccountNonLocked.equals(other.isAccountNonLocked))
			return false;
		if (isCredentialsNonExpired == null) {
			if (other.isCredentialsNonExpired != null)
				return false;
		} else if (!isCredentialsNonExpired
				.equals(other.isCredentialsNonExpired))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}

	
	
}
