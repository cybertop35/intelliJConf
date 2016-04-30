package com.fantagame.be.business.data.dataload.parse.IFace;


public interface IParse <in,out> {

	public void setValue(in in);
	
	public out parse() throws Exception;
}
