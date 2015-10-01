package com.isp.service;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(name = "executeDailyBatch", serviceName = "executeDailyBatchWS")
@Stateless
public class ExCall {

	@WebMethod
	@WebResult(name = "Result")
	public String executeWorkflow(String param )
	{
		return null;
	}
	
}
