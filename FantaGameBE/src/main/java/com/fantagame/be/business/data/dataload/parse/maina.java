package com.fantagame.be.business.data.dataload.parse;

import java.io.FileInputStream;
import java.net.MalformedURLException;

public class maina {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			
			String uff = "C:/MyFolder/Progetti/Game/Standard/FantaGameBE";
			String casa = "G:/Development/Progetti/Applications/Game/Standard/FantaGameBE";
			FileInputStream f = new FileInputStream(uff+"/src/main/resources/FileData/calendario.xls");
			
			ParseCalendario p =  new ParseCalendario();
			p.setValue(f);
			p.parse();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
