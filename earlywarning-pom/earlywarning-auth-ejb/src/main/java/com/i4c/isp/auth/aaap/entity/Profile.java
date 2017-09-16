package com.i4c.isp.auth.aaap.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FWS_01_PROFILE_MAPPING")
public class Profile  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 862818871492001846L;

	@EmbeddedId
	private ProfileId profileId;

	public Profile() {
		super();
		profileId = new  ProfileId(); 
	}
	
	public Profile(String systemProfile, String aaapProfile) {
		super();
		profileId = new  ProfileId(systemProfile,aaapProfile);
	}
	
	public String getSystemProfile() {
		return profileId.getSystemProfile();
	}
	
	public String getAaapProfile() {
		return profileId.getAaapProfile();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((profileId == null) ? 0 : profileId.hashCode());
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
		Profile other = (Profile) obj;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		return true;
	}
	
	
	
}
