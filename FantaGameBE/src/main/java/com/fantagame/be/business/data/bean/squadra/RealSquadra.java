package com.fantagame.be.business.data.bean.squadra;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.fantagame.be.business.data.bean.squadra.Iface.Rosa;
import com.fantagame.be.business.data.bean.squadra.rosa.Giocatore;
import com.fantagame.be.util.FileUtil;

@Entity
@Table(name="Squadra")
@XmlRootElement(name = "RealSquadra")
public class RealSquadra  extends AbstractSquadra implements Rosa<Giocatore>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2445060692866697640L;
	
	private static Logger log  = Logger.getLogger(RealSquadra.class);
	
	@OneToMany(mappedBy="realSquadra",fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<Giocatore> rosa;	
	
	@Lob
	@Column(name="Img")
	private byte[] img;
	
	public RealSquadra( ) {
		super();
	}
	
	public RealSquadra(Integer idSquadra, String nome) {
		super(idSquadra, nome);
	}

	public RealSquadra(Integer idSquadra, String nome, InputStream logo) {
		super(idSquadra, nome);
		try {
			this.setPhotoContent(logo);
		} catch (IOException e) {
			log.error(e);
		}
	}


	/**
	 * @return the img
	 */
	public byte[] getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(byte[] img) {
		this.img = img;
	}

	/**
	 * Returns the photo Input Stream.
	 * @return InputStream 
	 * @throws SQLException e
	 */
	public InputStream getPhotoContent() throws SQLException {
		if (getImg() == null) {
			return null;
		}
		
		return  new ByteArrayInputStream( getImg());
	}
	
	/**
	 * 
	 * @param sourceStream - Photo source input stream
	 * @throws IOException e
	 */
	public void setPhotoContent(InputStream in) throws IOException {
		setImg(FileUtil.loadBytesFromStreamForSize(in, in.available()));
	}
	/**
	 * @return the rosa
	 */
	public List<Giocatore> getRosa() {
		return rosa;
	}

	/**
	 * @param rosa the rosa to set
	 */
	public void setRosa(List<Giocatore> rosa) {
		this.rosa = rosa;
	}
}
