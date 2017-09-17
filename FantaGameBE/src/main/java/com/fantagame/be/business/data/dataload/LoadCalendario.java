package com.fantagame.be.business.data.dataload;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Calendario;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.data.dataload.IFace.IDataLoad;
import com.fantagame.be.business.data.dataload.exceptions.InvalidLoadingException;
import com.fantagame.be.business.data.dataload.exceptions.InvalidParseException;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;
 
public class LoadCalendario implements IDataLoad<InputStream , List<Calendario>> {

	private static org.apache.log4j.Logger log = Logger.getLogger(LoadCalendario.class);
	
	private String url;
	private IParse<InputStream, List<Calendario>> parse;
	
	private GenericDAO<Calendario, Integer> dao;
	
	
	public LoadCalendario() {
		super();
	}

	public LoadCalendario(String url, IParse<InputStream, List<Calendario>> parse) {
		super();
		this.url = url;
		this.parse = parse;
	}

	public void setDao(GenericDAO<Calendario, Integer> dao){
		this.dao = dao;
	}
	
	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setParse(IParse<InputStream, List<Calendario>> parse) {
		this.parse = parse;
		
	}	

	@Override
	public void load() {
		List<Calendario> obj = null;
		if(parse == null)
			new InvalidParseException("Invalid PARSE: NULL", DataConstants.DATALOADING_LOGGIN, null);
		if(url == null)
			new InvalidLoadingException("Invalid URL: NULL", DataConstants.DATALOADING_LOGGIN, this.getClass());
		
		try {
		
			InputStream in = new FileInputStream(url);
			parse.setValue(in);		
		
		} catch (Exception e) {
			new InvalidLoadingException(e.getMessage(), DataConstants.DATALOADING_LOGGIN, this.getClass());
		}
		
		try {
			 obj = parse.parse();
		} catch (Exception e) {
			new InvalidParseException(e.getMessage(), DataConstants.DATALOADING_LOGGIN, parse.getClass());
		}
		
		if(obj !=null)
			writeDataOnDB(obj);
	}

	private void writeDataOnDB(List<Calendario> obj){
		 int countError = 0;
		
		 for (Calendario c : obj){
			 try{
				 dao.merge(c);
			 }catch(Exception e){
				 countError++;
			 }
		 }
		
		 log.info("Count Giornate:" +obj.size()+ 		" Error Insert: "+countError);
	}	

}
