package com.fantagame.be.business.data.dataload;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.Andamento;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.data.dataload.IFace.IDataLoad;
import com.fantagame.be.business.data.dataload.exceptions.InvalidLoadingException;
import com.fantagame.be.business.data.dataload.exceptions.InvalidParseException;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;

public class LoadVoti implements IDataLoad<InputStream, List<Andamento>> {
	
	private static org.apache.log4j.Logger log = Logger.getLogger(LoadVoti.class);

	private String url;
	private IParse<InputStream, List<Andamento>> parse;
	private GenericDAO<Andamento, Integer> andamentoDao;
	
	public LoadVoti(){
		super();
	}
	
	public GenericDAO<Andamento, Integer> getAndamentoDao() {
		return andamentoDao;
	}

	public void setAndamentoDao(GenericDAO<Andamento, Integer> andamentoDao) {
		this.andamentoDao = andamentoDao;
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
	public void setParse(IParse<InputStream, List<Andamento>> parse) {
		this.parse = parse;
		
	}

	@Override
	public void load() {
		if(parse == null)
			new InvalidParseException("Invalid PARSE: NULL", DataConstants.DATALOADING_LOGGIN, null);
		if(url == null)
			new InvalidLoadingException("Invalid URL: NULL", DataConstants.DATALOADING_LOGGIN, this.getClass());
		
		InputStream isPage = null;
		try {
			isPage = new FileInputStream(url);
		
			parse.setValue(isPage);
			List<Andamento> obj = parse.parse();
			if(obj == null)
				throw new IllegalArgumentException();
			
			writeObjOnDB(obj);
		
		} catch (Exception e) {
			new InvalidLoadingException("Stream error",DataConstants.DATALOADING_LOGGIN, this.getClass());
		}
		
	}
	

	private void writeObjOnDB(List<Andamento> obj) {
		int countInsAndamentoErr = 0;
		StringBuilder strMessage = new StringBuilder();
		for(Andamento andamento : obj){
			try{
				andamentoDao.merge(andamento);
			}catch(Exception e){
				strMessage.append("ID: "+andamento.getId()+ " Error Message: "+e.getMessage()+"\n");
				countInsAndamentoErr++;
			}
		}
	
		log.info("Count Andamento:" +obj.size()+ " Error Insert: "+countInsAndamentoErr);
		
		if(countInsAndamentoErr > 0)
			log.error(strMessage.toString());
	}
	

}
