package it.Marco.Controller;

import it.Marco.Common.XmlUtil;
import it.Marco.GUI.Principale;

import java.io.File;

import org.jdom.Document;

public class Loader {
	
	
	private XmlUtil xmlUtil = null;
	private Document doc = null;
	private String smtp;
	private String pop3;
	private String username;
	private String pws;
	private String mailto;
	private String object;
	private String body;
	private String url;
	private String ipRilevato;
	private String tempo;
	private boolean autoRilevamento;
	private File file = null;
	private DatiObj dati = null;
	private String patern = null;
	static String temp;
	
	public Loader(){
		xmlUtil =  new XmlUtil();
	}
	
private DatiObj carica(){
	
	patern = System.getProperty("user.dir")+File.separator+"configuration.xml";
	file = new File(patern);
	
	if (file.exists()){
	
		doc =xmlUtil.createJdomDocument(file);
	
		smtp = doc.getRootElement().getChild("Email").getChildText("SMTP");
		pop3 = doc.getRootElement().getChild("Email").getChildText("pop3");
		username = doc.getRootElement().getChild("Email").getChildText("UserName");
		pws = doc.getRootElement().getChild("Email").getChildText("Password");
		mailto = doc.getRootElement().getChild("Email").getChildText("MailTo");
		object = doc.getRootElement().getChild("Email").getChildText("Object");
		body = doc.getRootElement().getChild("Email").getChildText("body");
		url = doc.getRootElement().getChild("ConfIp").getChildText("server");
		ipRilevato = doc.getRootElement().getChild("ConfIp").getChildText("Ip");
		tempo = doc.getRootElement().getChild("ConfIp").getChildText("watingTime");
		autoRilevamento = new Boolean(doc.getRootElement().getChild("ConfIp").getChildText("AutoRilevamento"));
		if (autoRilevamento){
			ipRilevato = rilevaip();
			if(ipRilevato == null)
				ipRilevato ="";
				 temp="Impossibile rilevare Ip.controllare la connessione";
		}
		
	}
	else
		return  new DatiObj("","","","","","","","","","",false);
	
	return new DatiObj(smtp,pop3,username,pws,mailto,object,body,url,ipRilevato,tempo,autoRilevamento);
}

private String rilevaip(){
	Http http = new Http(url);
	Object risp[]=http.loadPage();

	String ip =null;
	if(risp[0].equals("OK"))
		ip = xmlUtil.getFoglia((String)risp[1],"body" );
				
	
	return ip;
}
public static void main(String Args[]){
	Loader l = new Loader();
	Principale p = new Principale(l.carica());
	Traybar tb =  new Traybar(p);
	tb.trayIcon();
	p.setText(temp);
	
}
}
