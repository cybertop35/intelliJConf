package com.fantagame.be.business.data.dataload.parse;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.Resource;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.fantagame.be.business.data.bean.squadra.RealSquadra;
import com.fantagame.be.business.data.bean.squadra.rosa.Giocatore;
import com.fantagame.be.business.data.bean.squadra.rosa.Ruolo;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;
import com.fantagame.be.application.*;

public class SGRParseExcel implements IParse<InputStream, HashMap<String, List<?>>>{

	private int columns = 5;
	private int stratRow= 4;
	
	private int columnId = 0;
	private int columnNome = 1;
	private int columnRuolo = 2;
	private int columnSquadra = 3;
	private int columnQuota = 4;
	
	private int idSqudra = 1;
	private int idRuolo = 1;
	
	private InputStream in;
	
	public static final String GET_SQUADRA 		= "s";
	public static final String GET_RUOLO		= "R";
	public static final String GET_GIOCATORE 	= "G";
	
	@Override 
	public void setValue(InputStream in) {
	
		this.in = in;
		
	}

	@Override
	public HashMap<String, List<?>> parse() throws Exception {
		if(in == null)
			throw new IllegalArgumentException (); 
		
		LoadResources loadResources = null;
		
		HashMap<String,Integer> squdraMap = new HashMap<String,Integer>();
		HashMap<String,Integer> ruoliMap = new HashMap<String,Integer>();
		List<Giocatore>	giocatori = new ArrayList<Giocatore>();
		
		try {
			loadResources = (LoadResources) ApplicationContextProvider.getApplicationContext().getBean("resourceLoad");	
			Workbook w = Workbook.getWorkbook(in);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int i = stratRow; i < sheet.getRows(); i++) {
				Giocatore g = new Giocatore();
				for (int j = 0; j < columns; j++) {
					Cell cell = sheet.getCell(j, i);
					
					String cellValue = cell.getContents().toUpperCase();
					
					if(j == columnId){
						g.setIdGiocatore(Integer.parseInt(cellValue));
					}
					if(j == columnNome){
						g.setCognome(cellValue);
					}					
						
					if(j == columnRuolo){
						if(!ruoliMap.containsKey(cellValue)){
							ruoliMap.put(cellValue, idRuolo);
							idRuolo++;
						}
						g.setRuolo(new Ruolo(ruoliMap.get(cellValue), cellValue));
					}
					if(j == columnSquadra){
						if(!squdraMap.containsKey(cellValue)){
							squdraMap.put(cellValue, idSqudra);
							idSqudra++;
						}
						g.setRealSquadra(new RealSquadra(squdraMap.get(cellValue), cellValue));
					}
					if(j == columnQuota){
						g.setQuotazione(Integer.parseInt(cellValue));
					}
				}
				giocatori.add(g);
			}
			
		} catch (BiffException e) {
			throw e;
		}
	
		HashMap<String, List<?>> resuMap =  new HashMap<String, List<?>>();
		
		resuMap.put(GET_GIOCATORE, giocatori);
		
		List<Ruolo> ruoli = new ArrayList<Ruolo>();
		for (String key: ruoliMap.keySet()){
			ruoli.add(new Ruolo(ruoliMap.get(key),key));
		}
		resuMap.put(GET_RUOLO, ruoli);
		
		List<RealSquadra> realSquadras = new ArrayList<RealSquadra>();
		for (String key: squdraMap.keySet()){
			Resource r = loadResources.getLogo(key);
			if(r != null )
				realSquadras.add(new RealSquadra(squdraMap.get(key),key,r.getInputStream()));
			else
				realSquadras.add(new RealSquadra(squdraMap.get(key),key));
		}
		resuMap.put(GET_SQUADRA, realSquadras);
		
		idSqudra = 1;
		idRuolo = 1;
		
		return resuMap;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @return the stratRow
	 */
	public int getStratRow() {
		return stratRow;
	}

	/**
	 * @param stratRow the stratRow to set
	 */
	public void setStratRow(int stratRow) {
		this.stratRow = stratRow;
	}

	/**
	 * @return the columnId
	 */
	public int getColumnId() {
		return columnId;
	}

	/**
	 * @param columnId the columnId to set
	 */
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	/**
	 * @return the columnNome
	 */
	public int getColumnNome() {
		return columnNome;
	}

	/**
	 * @param columnNome the columnNome to set
	 */
	public void setColumnNome(int columnNome) {
		this.columnNome = columnNome;
	}

	/**
	 * @return the columnRuolo
	 */
	public int getColumnRuolo() {
		return columnRuolo;
	}

	/**
	 * @param columnRuolo the columnRuolo to set
	 */
	public void setColumnRuolo(int columnRuolo) {
		this.columnRuolo = columnRuolo;
	}

	/**
	 * @return the columnQuota
	 */
	public int getColumnQuota() {
		return columnQuota;
	}

	/**
	 * @param columnQuota the columnQuota to set
	 */
	public void setColumnQuota(int columnQuota) {
		this.columnQuota = columnQuota;
	}

	/**
	 * @return the columnSquadra
	 */
	public int getColumnSquadra() {
		return columnSquadra;
	}

	/**
	 * @param columnSquadra the columnSquadra to set
	 */
	public void setColumnSquadra(int columnSquadra) {
		this.columnSquadra = columnSquadra;
	}
	
}
