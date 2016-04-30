package it.marco.service;

import it.marco.Interface.ParserStrategy;
import it.marco.bean.MP3Bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;



public class YahooResult implements ListIterator<MP3Bean> {

	private static final String artistKEY = "Artist";
	private static final String songKEY = "song";

	private int totalResultsAvailable = 0, resultReturned = 10, position = 0;
	private ParserStrategy strategy;
	
	private MP3Bean seedBeen;
	
	private ArrayList<MP3Bean> list = new ArrayList<MP3Bean>();
	
	private YahooResult(MP3Bean seed, ParserStrategy strategy) {
		this.seedBeen = seed;
		this.strategy = strategy;
	}
	
	private void searchNext() throws Exception {
		String url = "http://search.yahooapis.com/AudioSearchService/V1/songSearch";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.addParameter("appid", "xxx");
		String title = seedBeen.getTitle();
		if (title != null && !title.equals("") )
			method.addParameter(songKEY, title);
		String artist = seedBeen.getAlbum();
		if (artist != null && !artist.equals(""))
			method.addParameter(artistKEY, artist);
		method.addParameter("start", (position+1)+"");
		method.addParameter("output", strategy.getOutput());
		int statusCode = client.executeMethod(method);
		
	    if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + method.getStatusLine());
        }
        
   
		InputStream stream = method.getResponseBodyAsStream();
		BufferedReader reader =  new BufferedReader(new InputStreamReader(stream));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			sb.append(line);
		}
		reader.close();
		
		MP3Bean[] items = strategy.parse(sb.toString());
		totalResultsAvailable = strategy.getTotalResultsAvailable();
		
		for (MP3Bean bean : items) {
			list.add(bean);
		}
				
	}
	
	public static YahooResult search(MP3Bean seed, ParserStrategy strategy) {
		YahooResult result = new YahooResult(seed,strategy);
		try {
			result.searchNext();
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public void add(MP3Bean e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MP3Bean next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MP3Bean previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(MP3Bean e) {
		// TODO Auto-generated method stub
		
	}

}
