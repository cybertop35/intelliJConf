package com.fantagame.be.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	public static byte[] BufferedImageToByte(BufferedImage in) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write( in, "jpg", baos );
			imageInByte = baos.toByteArray();
		} catch (IOException e) {
			throw new IOException();
		}
		finally{
			if(baos != null){
				try {
					baos.flush();
					baos.close();
				} catch (IOException e) {}
				
			}
		}
			
		
		return imageInByte;
	}
	
	public static BufferedImage resizeImageWithHint(InputStream in,int IMG_WIDTH,int IMG_HEIGHT) throws Exception {
		BufferedImage originalImage;
		BufferedImage resizedImage = null;
		try {
			originalImage = ImageIO.read(in);
			resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();	
			g.setComposite(AlphaComposite.Src);
		 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		} catch (IOException e) {
			throw new Exception();
		}
		
	 
		return resizedImage;
	}	
	
}
