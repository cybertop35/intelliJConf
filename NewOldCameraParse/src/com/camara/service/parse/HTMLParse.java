package com.camara.service.parse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.camara.service.bean.Product;

public class HTMLParse {

	private ArrayList<Product> products;
	private String url;
	private URL uri;
	private static final int timeout = 120000;
	
	public HTMLParse (String url){
		this.url = url;
		products = new ArrayList<Product>();
		try {
			uri = new URL(url);				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
		
	public ArrayList<Product> getProducts()
	{
		return products;
	}
	
	public void resetProducts(){
		products = null;
	}
	
	public void refresh(){
		resetProducts();
		try {
			Document doc = getDorcument();
			Element productsTable = getProductsElement(doc);
			Elements productsTr = productsTable.getElementsByTag("tr");
			System.out.print(productsTr);
			productsTr.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Element getProductsElement(Document doc){
		Element body = doc.body();
		Elements tables = body.getElementsByTag("tbody");
		Element productstable = null;
		for ( Element e : tables){
			Element elemeentMarca = e.getElementById("marca");
			if(elemeentMarca != null){
				productstable = e;
				break;
			}
		}
		return productstable;
	}
	
	private Document getDorcument() throws IOException{
		return Jsoup.parse(uri, timeout);
	}
	
	/****** TEST *****/
	public static void main(String args[]){
		String url ="http://www.newoldcamera.it/novita.aspx";
		HTMLParse h = new HTMLParse(url);
		h.refresh();
	}
}
