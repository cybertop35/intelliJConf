package com.fantagame.be.business.data.dataload.parse;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.fantagame.be.business.data.bean.Calendario;
import com.fantagame.be.business.data.bean.squadra.RealSquadra;
import com.fantagame.be.business.data.dao.Iface.GenericDAO;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;
import com.fantagame.be.util.StringUtils;


public class ParseCalendario implements IParse<InputStream, List<Calendario>>  {

	private InputStream in;
	private List<Calendario> list;
	private HashMap<String, RealSquadra> mapSquadre;
	private GenericDAO<RealSquadra, Integer> daoSquadra;
	
	private int stratRow= 0;
	
	private int columnGiornata		= 0;
	private String strGiornataParse	= "(0,2)";
	private String strDateParse 	= "(10,MAX)";
	private String dateFormat 		= "dd/MM/yyyy";
	
	private int columnSquadre		= 0;
	private int columnResA		 	= 1;
	private int columnResB		 	= 2;
	
	private int columnSalt 			= 3;
	private int columnsRep			= 3;
	
	private int rowsSalt 			= 12;
	private int rowGiornata 		= 0;
	
	private String squadreSeparetor = "-";
	private char risulatoSeparetor 	= '-';
	
	private String campionato;
	private String categoria;
	
	@Override
	public void setValue(InputStream in) {
		this.in = in;		
	}

	@Override
	public List<Calendario> parse() throws Exception {
		if(in == null)
			throw new IllegalArgumentException();
		
		list = new ArrayList<Calendario>();
		mapSquadre = new HashMap<String, RealSquadra>();
		boolean isFirst = true;
		
		try {
				Workbook w = Workbook.getWorkbook(in);
				// Get the first sheet
				Sheet sheet = w.getSheet(0);
				// Loop over first 10 column and lines
				
				for(int y = 0; y < columnsRep;y++){ 
					int giornata = -1 ;
					Date date = null;
					
					for (int i = stratRow; i < sheet.getRows()-1; i++){ 
						try{
							if(sheet.getCell(y+columnSalt, i).getContents().equalsIgnoreCase("") && 
									sheet.getCell(y+columnSalt+1, i).getContents().equalsIgnoreCase("") &&
									sheet.getCell(y+columnSalt+2, i).getContents().equalsIgnoreCase("")){
								break;
							}
							if(sheet.getCell(columnSquadre+(columnSalt * y), i).getContents().equalsIgnoreCase( "squadre"))
								continue;
							
							
														
							if(i == rowGiornata || ( i%rowsSalt)==0){
								Cell cell = sheet.getCell(columnGiornata+(columnSalt * y), i);
								String value = cell.getContents().toUpperCase();
								System.out.println("Value: "+value);
								int [] coo = StringUtils.getSubStrCoor(strGiornataParse);
								giornata = StringUtils.firstNumberInString(value.substring(coo[0],coo[1]));
		 								
								coo = StringUtils.getSubStrCoor(strDateParse,value);
								SimpleDateFormat format =  new SimpleDateFormat(dateFormat);
								date = format.parse(value.substring(coo[0],coo[1]));
								if(i != 0 )
									isFirst = false;
								continue;
							}					
						
							String sqd [] = sheet.getCell(columnSquadre+(columnSalt * y), i).getContents().toUpperCase().split(squadreSeparetor);
							
							String sqA = sqd[0].trim();
							String sqB = sqd[1].trim();
							System.out.println(  sqA+ "-"+  sqB);		
							if(isFirst){
								mapSquadre.put(sqA, daoSquadra.findByProperties(new RealSquadra(null, sqA)).get(0));
								mapSquadre.put(sqB, daoSquadra.findByProperties(new RealSquadra(null, sqB)).get(0));
								
							}

							Calendario c = new Calendario(mapSquadre.get(sqA),mapSquadre.get(sqB),giornata,date,campionato,categoria);
	
							c.setCampionato(campionato);
							c.setCategoria(categoria);
							
							if(sheet.getCell(columnResA+(columnSalt * y), i).getContents().length() > 0)
								c.setRisultatoA(Integer.parseInt(sheet.getCell(columnResA+(columnSalt * y), i).getContents()));
							
							if(sheet.getCell(columnResB+(columnSalt * y), i).getContents().length() > 0)
								c.setRisultatoB(Integer.parseInt(sheet.getCell(columnResB+(columnSalt * y), i).getContents()));
							

							
							list.add(c);
						}catch(Exception e){
							System.err.print(e);
						}
					}
				}
				
			
			} catch (Exception e) {
				System.err.print(e);
			}
		
		return list;

	}

	public GenericDAO<RealSquadra, Integer> getDaoSquadra() {
		return daoSquadra;
	}

	public void setDaoSquadra(GenericDAO<RealSquadra, Integer> daoSquadra) {
		this.daoSquadra = daoSquadra;
	}

	public int getStratRow() {
		return stratRow;
	}

	public void setStratRow(int stratRow) {
		this.stratRow = stratRow;
	}

	public int getColumnGiornata() {
		return columnGiornata;
	}

	public void setColumnGiornata(int columnGiornata) {
		this.columnGiornata = columnGiornata;
	}

	public String getStrGiornataParse() {
		return strGiornataParse;
	}

	public void setStrGiornataParse(String strGiornataParse) {
		this.strGiornataParse = strGiornataParse;
	}

	public String getStrDateParse() {
		return strDateParse;
	}

	public void setStrDateParse(String strDateParse) {
		this.strDateParse = strDateParse;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getColumnSquadre() {
		return columnSquadre;
	}

	public void setColumnSquadre(int columnSquadre) {
		this.columnSquadre = columnSquadre;
	}

	public int getColumnResA() {
		return columnResA;
	}

	public void setColumnResA(int columnResA) {
		this.columnResA = columnResA;
	}

	public int getColumnResB() {
		return columnResB;
	}

	public void setColumnResB(int columnResB) {
		this.columnResB = columnResB;
	}

	public int getColumnSalt() {
		return columnSalt;
	}

	public void setColumnSalt(int columnSalt) {
		this.columnSalt = columnSalt;
	}

	public int getColumnsRep() {
		return columnsRep;
	}

	public void setColumnsRep(int columnsRep) {
		this.columnsRep = columnsRep;
	}

	public int getRowsSalt() {
		return rowsSalt;
	}

	public void setRowsSalt(int rowsSalt) {
		this.rowsSalt = rowsSalt;
	}

	public String getSquadreSeparetor() {
		return squadreSeparetor;
	}

	public void setSquadreSeparetor(String squadreSeparetor) {
		this.squadreSeparetor = squadreSeparetor;
	}

	public char getRisulatoSeparetor() {
		return risulatoSeparetor;
	}

	public void setRisulatoSeparetor(char risulatoSeparetor) {
		this.risulatoSeparetor = risulatoSeparetor;
	}

	public String getCampionato() {
		return campionato;
	}

	public void setCampionato(String campionato) {
		this.campionato = campionato;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	
	
}
