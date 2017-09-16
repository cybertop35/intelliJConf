package com.i4c.isp.auth.aaap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProfileId implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705850054866095709L;
	
	
	@Column(name= "SYSTEM_PROFILE")
	private String systemProfile;
	@Column(name="AAAP_PROFILE")
	private String aaapProfile;
	
	public ProfileId() {
		super();
	}
	
	

	public ProfileId(String systemProfile, String aaapProfile) {
		super();
		this.systemProfile = systemProfile;
		this.aaapProfile = aaapProfile;
	}



	public String getSystemProfile() {
		return systemProfile;
	}

	public void setSystemProfile(String systemProfile) {
		this.systemProfile = systemProfile;
	}

	public String getAaapProfile() {
		return aaapProfile;
	}

	public void setAaapProfile(String aaapProfile) {
		this.aaapProfile = aaapProfile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aaapProfile == null) ? 0 : aaapProfile.hashCode());
		result = prime * result
				+ ((systemProfile == null) ? 0 : systemProfile.hashCode());
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
		ProfileId other = (ProfileId) obj;
		if (aaapProfile == null) {
			if (other.aaapProfile != null)
				return false;
		} else if (!aaapProfile.equals(other.aaapProfile))
			return false;
		if (systemProfile == null) {
			if (other.systemProfile != null)
				return false;
		} else if (!systemProfile.equals(other.systemProfile))
			return false;
		return true;
	}
	
	
	

}
