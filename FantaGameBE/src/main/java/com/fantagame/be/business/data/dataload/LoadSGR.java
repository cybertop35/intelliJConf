package com.fantagame.be.business.data.dataload;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.DataConstants;
import com.fantagame.be.business.data.bean.squadra.RealSquadra;
import com.fantagame.be.business.data.bean.squadra.rosa.Giocatore;
import com.fantagame.be.business.data.bean.squadra.rosa.Ruolo;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.data.dataload.IFace.IDataLoad;
import com.fantagame.be.business.data.dataload.exceptions.InvalidLoadingException;
import com.fantagame.be.business.data.dataload.exceptions.InvalidParseException;
import com.fantagame.be.business.data.dataload.parse.SGRParseExcel;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;
import com.fantagame.be.util.FileUtil;
 
public class LoadSGR implements IDataLoad<InputStream, HashMap<String, List<?>>>{

	private String url;
	private IParse<InputStream, HashMap<String, List<?>>> parse;
	private boolean isZip = true;
	
	private GenericDAO<Giocatore, Integer> daoGiocatore;
	private GenericDAO<Ruolo, Integer> daoRuolo;
	private GenericDAO<RealSquadra, Integer> daoRealSquadra;
	
	private static org.apache.log4j.Logger log = Logger.getLogger(LoadSGR.class);
	/**
	 * @return the isZip
	 */
	public boolean isZip() {
		return isZip;
	}

	/**
	 * @param isZip the isZip to set
	 */
	public void setZip(boolean isZip) {
		this.isZip = isZip;
	}
	
	

	/**
	 * @param daoGiocatore the daoGiocatore to set
	 */
	public void setDaoGiocatore(GenericDAO<Giocatore, Integer> daoGiocatore) {
		this.daoGiocatore = daoGiocatore;
	}

	/**
	 * @param daoRuolo the daoRuolo to set
	 */
	public void setDaoRuolo(GenericDAO<Ruolo, Integer> daoRuolo) {
		this.daoRuolo = daoRuolo;
	}

	/**
	 * @param daoRealSquadra the daoRealSquadra to set
	 */
	public void setDaoRealSquadra(GenericDAO<RealSquadra, Integer> daoRealSquadra) {
		this.daoRealSquadra = daoRealSquadra;
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
	public void setParse(IParse<InputStream, HashMap<String, List<?>>> parse) {
		this.parse = parse;		
	}

	@Override
	public void load() {
		if(parse == null)
			new InvalidParseException("Invalid PARSE: NULL", DataConstants.DATALOADING_LOGGIN, null);
		if(url == null)
			new InvalidLoadingException("Invalid URL: NULL", DataConstants.DATALOADING_LOGGIN, this.getClass());
		
		//downLoadFile
		InputStream isPage = null;
		try {
			isPage = new FileInputStream(url);
			InputStream doc = null;
			
			//Estraggo il file
			if(isZip){
				List<InputStream> streams = FileUtil.estraiZip(isPage);
				if(streams != null & streams.size() > 0)
					doc = streams.get(0);
			}
			else{
				doc = isPage;
			}
			parse.setValue(doc);
			HashMap<String, List<?>> obj = parse.parse();
			writeObjOnDB(obj);
		
		} catch (Exception e) {
			new InvalidLoadingException("Stream error",DataConstants.DATALOADING_LOGGIN, this.getClass());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void writeObjOnDB(HashMap<String, List<?>> obj) {
		
		List<Giocatore>	giocatori = (List<Giocatore>) obj.get(SGRParseExcel.GET_GIOCATORE);
		List<Ruolo>	ruoli = (List<Ruolo>) obj.get(SGRParseExcel.GET_RUOLO);
		List<RealSquadra>	squdre = (List<RealSquadra>) obj.get(SGRParseExcel.GET_SQUADRA);
		
		int countInsSqudraErr = 0;
		int countInsRuoloErr = 0;
		int countInsGiocatoreErr = 0;
		
		for(RealSquadra squadra : squdre){
			try{
				daoRealSquadra.merge(squadra);
			}catch(Exception e){
				countInsSqudraErr++;
			}
		}
		for(Ruolo ruolo : ruoli){
			try {
				daoRuolo.merge(ruolo);
			}catch(Exception e){
				countInsRuoloErr++;
			}
		}
		for(Giocatore giocatore : giocatori){
			try{
				daoGiocatore.merge(giocatore);
			}catch(Exception e){
				countInsGiocatoreErr++;
			}
		}
		log.info("Count Squadre:" +squdre.size()+ 		" Error Insert: "+countInsSqudraErr);
		log.info("Count Ruoli:" +ruoli.size()+ 			" Error Insert: "+countInsRuoloErr);
		log.info("Count Giocatori:" +giocatori.size()+ 	" Error Insert: "+countInsGiocatoreErr);
	}

}
