package com.fantagame.be.business.data.dataload.parse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.fantagame.be.business.data.bean.Andamento;
import com.fantagame.be.business.data.dataload.parse.IFace.IParse;

public class ParseVotiExcel implements IParse<InputStream, List<Andamento>> {

	private InputStream in;

	private int stratRow= 4;
	
	private int columnId 		= 0;
	private int columnVG 		= 4;
	private int columnGolSub 	= 6;
	private int columnGolFat 	= 5;
	private int columnVCDS 		= 9;
	private int columnAmm 		= 21;
	private int columnEsp 		= 22;
	private int columnTitolare 	= 29;
	private int columnFVG 		= 30;
	private int columnFVCDS 	= 31;
	
	private String rowColumnGiornata = "(1,0)";
	
	public ParseVotiExcel(){
		super();
	}
		
	public int getStratRow() {
		return stratRow;
	}

	public void setStratRow(int stratRow) {
		this.stratRow = stratRow;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public int getColumnVG() {
		return columnVG;
	}

	public void setColumnVG(int columnVG) {
		this.columnVG = columnVG;
	}

	public int getColumnGolSub() {
		return columnGolSub;
	}

	public void setColumnGolSub(int columnGolSub) {
		this.columnGolSub = columnGolSub;
	}

	public int getColumnGolFat() {
		return columnGolFat;
	}

	public void setColumnGolFat(int columnGolFat) {
		this.columnGolFat = columnGolFat;
	}

	public int getColumnVCDS() {
		return columnVCDS;
	}

	public void setColumnVCDS(int columnVCDS) {
		this.columnVCDS = columnVCDS;
	}

	public int getColumnAmm() {
		return columnAmm;
	}

	public void setColumnAmm(int columnAmm) {
		this.columnAmm = columnAmm;
	}

	public int getColumnEsp() {
		return columnEsp;
	}

	public void setColumnEsp(int columnEsp) {
		this.columnEsp = columnEsp;
	}

	public int getColumnTitolare() {
		return columnTitolare;
	}

	public void setColumnTitolare(int columnTitolare) {
		this.columnTitolare = columnTitolare;
	}

	public int getColumnFVG() {
		return columnFVG;
	}

	public void setColumnFVG(int columnFVG) {
		this.columnFVG = columnFVG;
	}

	public int getColumnFVCDS() {
		return columnFVCDS;
	}

	public void setColumnFVCDS(int columnFVCDS) {
		this.columnFVCDS = columnFVCDS;
	}

	public String getRowColumnGiornata() {
		return rowColumnGiornata;
	}

	public void setRowColumnGiornata(String rowColumnGiornata) {
		this.rowColumnGiornata = rowColumnGiornata;
	}

	@Override
	public void setValue(InputStream in) {
		this.in = in;
		
	}

	@Override
	public List<Andamento> parse() throws Exception {
		if(in == null)
			throw new IllegalArgumentException();
		
		List<Andamento> listAndamento = new ArrayList<Andamento>();
		
		try {
			Workbook w = Workbook.getWorkbook(in);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			int giornata = getGiornata(sheet);
			for (int i = stratRow; i < sheet.getRows(); i++){ 
				Andamento andamento = new Andamento();
				andamento.setData(new Date());
				andamento.setGiornata(giornata);
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					//System.out.println("J: "+j+" i: "+i+" Value: "+ cell.getContents());
					boolean noVoto = cell.getContents().equalsIgnoreCase("s,v,");
					if(j == columnId){
						andamento.setIdGiocatore(Integer.parseInt(cell.getContents().replace(",", ".")));
					}
					if(j == columnVG && !noVoto){
						andamento.setVotoG(Double.parseDouble(cell.getContents().replace(",", ".")));
					}					
						
					if(j == columnGolSub){
						andamento.setGolSubiti(Integer.parseInt(cell.getContents()));
					}
					if(j == columnGolFat){
						andamento.setGolFatti(Integer.parseInt(cell.getContents()));
					}
					if(j == columnVCDS && !noVoto){
						andamento.setVotoCDS(Double.parseDouble(cell.getContents().replace(",", ".")));
					}
					if(j == columnAmm){
						andamento.setAmmonito(Boolean.parseBoolean(cell.getContents()));
					}
					if(j == columnEsp){
						andamento.setEspulso(Boolean.parseBoolean(cell.getContents()));
					}
					if(j == columnTitolare){
						andamento.setTitolare(Boolean.parseBoolean(cell.getContents()));
					}
					if(j == columnFVG && !noVoto){
						andamento.setFVG(Double.parseDouble(cell.getContents().replace(",", ".")));
					}
					if(j == columnFVCDS && !noVoto){
						andamento.setFVCDS(Double.parseDouble(cell.getContents().replace(",", ".")));
					}
					
				}
			listAndamento.add(andamento);
		}
		
	} catch (Exception e) {
		System.err.print(e);
	}
		
		return listAndamento;
	}

	private int getGiornata(Sheet sheet) {
		
		String[] coordinate = rowColumnGiornata.substring(1,rowColumnGiornata.length()-1).split(",");
		Cell c = sheet.getCell(Integer.parseInt(coordinate[1]),Integer.parseInt(coordinate[0]));
		String value = c.getContents();
		String v =  value.substring(value.indexOf(":")+1,value.length());
		return Integer.parseInt(v.trim());
	}

}
