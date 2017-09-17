package it.marco.bean;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.blinkenlights.jid3.util.ID3Visitor;
import org.blinkenlights.jid3.v1.ID3V1_1Tag;
import org.blinkenlights.jid3.v2.APICID3V2Frame;

public class MP3BeanVisitor extends ID3Visitor{

	private MP3Bean bean;
	
	public MP3BeanVisitor(MP3Bean bean) {
		this.bean = bean;
	}
	
	@Override
	public void visitID3V1_1Tag(ID3V1_1Tag tag) {
		String title = tag.getTitle();
		if (title != null)
			bean.setTitle(title);
		
		String album = tag.getAlbum();
		if (album != null)
			bean.setAlbum(album);
	}
	
	@Override
	public void visitAPICID3V2Frame(APICID3V2Frame frame) {
		byte[] contents = frame.getPictureData();
		ByteArrayInputStream stream = new ByteArrayInputStream(contents);
		try {
			Image image = ImageIO.read(stream);
			image = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			bean.setCover(image);
		} catch (IOException e) {
			
		} finally {
			try {
				stream.close();
			} catch (IOException e) {

			}
		}
		
	}
	
}
