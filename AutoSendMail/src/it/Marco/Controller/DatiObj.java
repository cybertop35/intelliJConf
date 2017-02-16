package it.Marco.Controller;

public class DatiObj {
	
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
	
	public DatiObj(String smtp, String pop3, String username, String pws,
			String mailto, String object, String body, String url,
			String ipRilevato, String tempo, boolean autoRilevamento) {
		super();
		this.smtp = smtp;
		this.pop3 = pop3;
		this.username = username;
		this.pws = pws;
		this.mailto = mailto;
		this.object = object;
		this.body = body;
		this.url = url;
		this.ipRilevato = ipRilevato;
		this.tempo = tempo;
		this.autoRilevamento = autoRilevamento;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPop3() {
		return pop3;
	}

	public void setPop3(String pop3) {
		this.pop3 = pop3;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPws() {
		return pws;
	}

	public void setPws(String pws) {
		this.pws = pws;
	}

	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIpRilevato() {
		return ipRilevato;
	}

	public void setIpRilevato(String ipRilevato) {
		this.ipRilevato = ipRilevato;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public boolean isAutoRilevamento() {
		return autoRilevamento;
	}

	public void setAutoRilevamento(boolean autoRilevamento) {
		this.autoRilevamento = autoRilevamento;
	}
	public DatiObj getDatObj(){
		return this;
	}
	

}
