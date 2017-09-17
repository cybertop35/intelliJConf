package com.fantagame.be.business.data.bean.Iface;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public interface IBean <T extends Serializable> {

	@JsonIgnore(value = true)
	T getId();
	
}
