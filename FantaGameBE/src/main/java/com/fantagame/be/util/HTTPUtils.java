package com.fantagame.be.util;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPUtils {

	public static String loadPage (URL url) throws Exception{
		String  result = null;
		DataInputStream page = null;
		try {
		         
		      page = new DataInputStream(url.openStream());
		      result =   page.readUTF();		      
		       
		}catch (MalformedURLException e) {
			throw new Exception("loadPage MalformedURLException", e);
		}
		catch (IOException e) {
			throw new Exception("loadPage IOException", e);
		}finally{
		   	try {
				page.close();
			} catch (IOException e) {
				throw new Exception("loadPage close stream", e);
			}
		}
		
		return result;
	}
	
	public static InputStream loadPageInputStream (URL url) throws Exception{
		DataInputStream page = null;
		try {
		     
		      page = new DataInputStream(url.openStream());
		         
		       
		}catch (MalformedURLException e) {
			throw new Exception("loadPage MalformedURLException", e);
		}
		catch (IOException e) {
			throw new Exception("loadPage IOException", e);
		}finally{
		   	try {
				page.close();
			} catch (IOException e) {
				throw new Exception("loadPage close stream", e);
			}
		}
		
		return page;
	}
	
	public static InputStream downLoadFile (URL url) throws Exception{
	
		InputStream is = null;
		OutputStream os = null;
		URLConnection URLConn = null;
		byte[] buf = null;
		try {
		
			
			@SuppressWarnings("unused")
			int ByteRead,ByteWritten=0;
		
			os = new BufferedOutputStream(new FileOutputStream("C:/jl.xls"));
			URLConn = url.openConnection();
			
			URLConn.setReadTimeout(1000000);
			is = URLConn.getInputStream();
			buf = new byte[1024];
			while ((ByteRead = is.read(buf)) != -1) {
				os.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			System.out.println("Downloaded Successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
				os.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ByteArrayInputStream(buf);
	}
}
