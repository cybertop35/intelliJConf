package com.i4c.isp.auth.aaap;

import java.util.List;

public interface ManagerProfileLocal {

	public List<String> getAAAPProfile(String externalProfile);
	public void reload();
}
