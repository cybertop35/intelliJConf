package com.fantagame.be.business.data.dataload.IFace;

import com.fantagame.be.business.data.dataload.parse.IFace.IParse;

public interface IDataLoad<in, out> {
	
	public void setUrl(String url);
	public String getUrl();
	public void setParse(IParse<in, out> parse);
	public void load();

}
