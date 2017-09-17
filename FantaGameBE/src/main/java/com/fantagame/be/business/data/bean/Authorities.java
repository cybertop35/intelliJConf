package com.fantagame.be.business.data.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;

import com.fantagame.be.business.data.bean.Iface.IBean;

@Entity
@Table(name="authorities")
@XmlRootElement(name = "Authorities")
public class Authorities implements GrantedAuthority,Serializable,IBean<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 837391670915012830L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAuthorities",nullable=false,unique=true)
	private Integer idAuthorities;
	
	@Column(name="Authority",nullable =false)
	private String authority;
	
    @ManyToOne
    @JoinColumn(name="nick")
    private UserLogin userLogin;
	
	public Authorities() {
		super();
	}

	public Authorities(String authority,UserLogin userLogin) {
		super();
		this.authority = authority;
		this.userLogin = userLogin;
	}

	/**
	 * @return the idAuthorities
	 */
	public Integer getIdAuthorities() {
		return idAuthorities;
	}

	/**
	 * @param idAuthorities the idAuthorities to set
	 */
	public void setIdAuthorities(Integer idAuthorities) {
		this.idAuthorities = idAuthorities;
	}

	/**
	 * @return the authority
	 */
	@Override
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

	/**
	 * @return the userLogin
	 */
	public UserLogin getUserLogin() {
		return userLogin;
	}

	/**
	 * @param userLogin the userLogin to set
	 */
	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authorities [idAuthorities=" + idAuthorities + ", Authority="
				+ authority + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result
				+ ((idAuthorities == null) ? 0 : idAuthorities.hashCode());
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
		Authorities other = (Authorities) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (idAuthorities == null) {
			if (other.idAuthorities != null)
				return false;
		} else if (!idAuthorities.equals(other.idAuthorities))
			return false;
		return true;
	}

	@Override
	public Integer getId() {
		return idAuthorities;
	}
	
	
}
