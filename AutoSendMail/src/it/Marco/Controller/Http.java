package it.Marco.Controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Http {

	private static HttpClient client = new HttpClient();
	private String[] risposta = null;
	private PostMethod post = null;
	private GetMethod get = null;
	private InputStream is = null;
	private String url = null;
	
	public Http(String url){
		this.url = url;
		
	}
	
	public String typeMethod(){
		
		String type = null;
		HttpMethod httpMethod = null;
		if (url != null){
			httpMethod = new GetMethod(url);
			type = httpMethod.getName();
			return type;
		}		
		return type;
	}
	
	public String[]loadPage() {
			
			int status = 0;
			risposta=new String[2];	
			post = new PostMethod(url);
			get =  new GetMethod(url);
			//client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			
			try {
				
				if (typeMethod().equals("POST")){
					
					status = client.executeMethod(post);
					
					if (client.executeMethod(post) == HttpStatus.SC_OK  ){
						is =post.getResponseBodyAsStream();
						String resp =inputStream2String(is);				 
						risposta[0]="OK";
						risposta[1]=resp;
						
						return risposta;
					}
				} 
				if (typeMethod().equals("GET")){
					status = client.executeMethod(get);
					if(client.executeMethod(get) == HttpStatus.SC_OK ){
						is=get.getResponseBodyAsStream();
						String resp =inputStream2String(is);				 
						risposta[0]="OK";
						risposta[1]=resp;
					 
						return risposta;
					}
				}
								
				risposta[0]="KO";
				risposta[1]="Operazione Fallita:"+HttpStatus.getStatusText(status);
				
				return risposta;				
				
			
			} catch (UnknownHostException uhe){
				risposta[0]="KO";
				risposta[1]=uhe.getMessage();
				return risposta;
			}catch
			(Exception ex) {
				ex.printStackTrace();
				risposta[0]="KO";
				risposta[1]=ex.getMessage();
				return risposta;			
			} finally {
				if (typeMethod().equals("GET"))
					get.releaseConnection();
				if (typeMethod().equals("POST"))
					post.releaseConnection();
			}							
			
		}	
	
	public String inputStream2String(InputStream is){
		
		InputStreamReader isr = new InputStreamReader(is);

		Reader in = new BufferedReader(isr);

		StringBuffer buf = new StringBuffer();
		int ch;
		
		try{			
			while ((ch = in.read()) > -1) {
				buf.append((char)ch);
			}
			in.close();
		}catch (Exception e) {
			return null;
		}
				
		String resp = buf.toString();
		return resp;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	

