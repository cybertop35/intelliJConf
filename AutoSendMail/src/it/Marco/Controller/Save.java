package it.Marco.Controller;

import it.Marco.GUI.Principale;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Save {
	
	private DatiObj datiObj = null;
	private org.jdom.Document doc = null;
	public Save(DatiObj datiObj) {
		super();
		this.datiObj = datiObj;
		doc =  new Document();
		
	}
	public boolean salva(){
		Element root = new Element("Configurazione");
		
		Element email = new Element("Email"),
		smtp= new Element("SMTP"),
		pop3 = new Element("pop3"),
		username = new Element("UserName"),
		pws =  new Element("Password"),
		mailto = new Element("MailTo"),
		object = new Element("Object"),
		body = new Element("body");
		
		smtp.setText(datiObj.getSmtp());
		pop3.setText(datiObj.getPop3());
		username.setText(datiObj.getUsername());
		pws.setText(datiObj.getPws());
		mailto.setText(datiObj.getMailto());
		object.setText(datiObj.getObject());
		body.setText(datiObj.getBody());
	
		
		email.addContent(smtp);
		email.addContent(pop3);
		email.addContent(username);
		email.addContent(pws);
		email.addContent(mailto);
		email.addContent(object);
		email.addContent(body);
		
		Element ConfIp =new Element ("ConfIp"),
		server = new Element("server"),
		ip = new Element("Ip"),
		tempo = new Element("watingTime"),
		autoRilevamento =  new Element("AutoRilevamento");
		
		server.setText(datiObj.getUrl());
		ip.setText(datiObj.getIpRilevato());
		tempo.setText(datiObj.getTempo());
		autoRilevamento.setText(new Boolean(datiObj.isAutoRilevamento()).toString());
		
		ConfIp.addContent(server);
		ConfIp.addContent(ip);
		ConfIp.addContent(tempo);
		ConfIp.addContent(autoRilevamento);
	
		root.addContent(email);
		root.addContent(ConfIp);
		
		doc.setRootElement(root);
		
	
		
		/**
		 * Scrive su file il contenuto del docimento
		 * xml creato
		 */
		try {
			
			String patern= System.getProperty("user.dir")+File.separator+"configuration.xml";
			File fi = new File(patern);
			fi.delete();
			fi.setReadOnly();		
			PrintWriter out = new PrintWriter(new FileWriter(patern,true));
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
		
			outputter.output(doc, out);
			Principale.setText("Salvataggio effttuato con successo. Grazie");
			out.flush();
			out.close();
		
			
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return false;
	}
	
		
}
