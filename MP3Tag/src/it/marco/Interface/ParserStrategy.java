package it.marco.Interface;

import it.marco.bean.MP3Bean;

public interface ParserStrategy {

	public String getOutput();
	public MP3Bean[] parse(String textToParse) throws Exception;
	public int getTotalResultsAvailable();
}
