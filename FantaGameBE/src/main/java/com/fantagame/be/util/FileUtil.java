package com.fantagame.be.util;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class FileUtil {
	
	
	/**
	 * OK
	 *  
	 * Questo metodo estrae i file contenuti in un file compreso,
	 * e li aggiunge in un arrayList
	 * 
	 * @param File f
	 * @return ArrayList<File>
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<File> estraiZip(File f) throws ClassNotFoundException{
		
		ArrayList<File>  listaFile = new ArrayList<File>();
		Enumeration<? extends ZipEntry> e = null;
		ZipFile zipfile =  null;
		ZipEntry ze = null;
		String patern = System.getProperty("user.dir");
		
    	File dir = new File (patern+File.separator+"temp");
    	FileOutputStream fos = null ;
    	File fileTemp;
    	byte [] dati;
		try {
        	
        	/**
        	 * Controllo se esiste la cratella Temp
        	 */
        	if(!dir.exists())
        		dir.mkdir();
        	
			if(isFileZip(f)){
				zipfile = new ZipFile(f);
				e = zipfile.entries();
				//rcupero i file contenuti
								
				while (e.hasMoreElements()){
					
					ze = e.nextElement();						
					InputStream ist = zipfile.getInputStream(ze);
					fos =  new FileOutputStream(dir+File.separator+ze.getName());						
					dati = new byte[ist.available()];
					
					ist.read(dati);
					fos.write(dati);
					ist.close();
					fos.close();
						
					fileTemp =  new File(dir+File.separator+ze.getName());
						
					listaFile.add( fileTemp );
					
					}
				}
			
			} catch (ZipException e1) {
				System.out.println("Errore 11 --> "+e1.toString());
			} catch (IOException e1) {
				System.out.println("Errore 12--> "+e1.toString());
			}finally{
				if(fos != null){
					try {
						fos.flush();
						fos.close();
					} catch (IOException e1) {
						
					}
				}
			}
		
		return listaFile;
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<InputStream> estraiZip(InputStream is) throws ClassNotFoundException{
		
		ArrayList<InputStream>  inputStreams= new ArrayList<InputStream>();		
		ZipInputStream zis =  null;
		  	
		try {
			
        	zis = new ZipInputStream(is);
			ZipEntry ze= null;				
			do{
				ze = zis.getNextEntry();
				
				String entryName = ze.getName();
		        System.out.println("entryname "+entryName);
				byte[] bs = loadBytesFromStreamForSize(zis,(int)ze.getSize());
		        InputStream in = new ByteArrayInputStream(bs);						
		        inputStreams.add( in );			
		        
		        ze.clone();
			
			}while (ze!= null);
			
		} catch (ZipException e1) {
			System.out.println("Errore 11 --> "+e1.toString());
		} catch (IOException e1) {
			System.out.println("Errore 12--> "+e1.toString());
		}finally{
			
				try {
					if(zis != null){
						zis.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
		}
		
		return inputStreams;
	}
	
	/**
	 * 
	 * @param in - InputStream
	 * @param size - InputStream size
	 * @return byte []
	 * @throws IOException
	 */
	public static byte[] loadBytesFromStreamForSize( InputStream in, int size )throws IOException {
		
		int count, index = 0;
		byte[] b = new byte[size];

		// read in the bytes from input stream
		while( ( count = in.read( b, index, size ) ) > 0 ) {
			size -= count;
			index += count;
		}
		return b;
	}
	
	/**
	 * OK
	 * 
	 * Verifico se il File  informato zip o jar
	 * 
	 * @param File
	 * @return boolean
	 */
	private static boolean isFileZip (File f){
		String name = f.getName();
		if(name.endsWith("zip")||name.endsWith("jar")||name.endsWith("rar"))
			return true;
		
		return false;
		
	}

	/**
	 * Questo metodo crea un file compresso(*.zip),
	 * contente tutti i file della lista
	 * 
	 * @param File listaFile[]
	 * @return FileZip
	 */
    public static File creaZip(File listaFile[])
    {
        File current = null;
        File risposta = null;
        ZipOutputStream out = null;
        byte buff[] = new byte[4096];
        
        try
        {
            out = new ZipOutputStream(new BufferedOutputStream( new FileOutputStream("c:/file.zip")));

            for(int i = 0; i < listaFile.length; i++)
            {
                current = listaFile[i];
                System.out.println(current.getName());
                FileInputStream in = new FileInputStream(current);
                out.setMethod(8);
                out.putNextEntry(new ZipEntry(current.getName()));
                int n;
                while((n = in.read(buff, 0, buff.length)) != -1) 
                    out.write(buff, 0, n);
                
                in.close();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println((new StringBuilder("ErroreCreoZip_FNFException --> ")).append(e.toString()).toString());
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder("ErroreCreoZip_IOException --> ")).append(e.toString()).toString());
        }
        finally{
        	if(out != null){
        		try {
					out.flush();
					out.close();
        		} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return risposta;
    }
    
    /**
     * OK
     * 
     * Questo metodo costruisce un ArrayList di file.
     * 
     * Ha come input il path di una cartella del fileSystem, 
     * aggiunge alla lista tutti i file contnuti nella directory
     * 
     * @param String path
     * @return ArrayList<File>
     */
    public static ArrayList<File> arreyFile(String path)
    {
        File dir = new File(path);
        ArrayList<File> listafile = new ArrayList<File>();
        if(dir.isDirectory())
        {
            for(int i = 0; i < dir.listFiles().length; i++)
                listafile.add(dir.listFiles()[i]);

            return listafile;
        } else
        {
            return listafile;
        }
    }
    
    /**
     * OK
     * 
     * Questo metodo elimina i file contenuti in 
     * una lista dal fileSystem.
     * 
     * @param listFile
     * @return boolean
     */
    public static boolean deleteFile(ArrayList<File> listFile){
    	File file;
    	for(int i = 0; i<listFile.size(); i++){    		
    		file = listFile.get(i);    		
    		if(!file.delete())
    			return false;
    	}
    	return true;
    }

    /**
     * OK
     * 
     * Aggiunge un generico file a uno zip passato come parametro
     * 
     * @param out
     * @param osw
     * @param name
     * @return
     */
    public static boolean addEntry(ZipOutputStream out, byte []osw, String name){
    	
    	try{
    		out.putNextEntry(new ZipEntry(name));    		
    		out.write(osw);    		
    	}catch(IOException e ){
    		return false;
    	}
    	
    	return true;
    	
    }

    public static boolean getZipFromArrayList(ArrayList<File> fileList, String nomeFileZip) {
    	FileOutputStream fos = null;
    	ZipOutputStream out = null;
    	try {	
    			fos = new FileOutputStream(nomeFileZip);
		
    			out = new ZipOutputStream (fos);
		
    			for(int i = 0; i<fileList.size(); i++){
		
    				File tmp = fileList.get(i);
		
    				String name = tmp.getName();
										
    				BufferedReader bfr = new BufferedReader(new FileReader(tmp));
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					DataOutputStream dos =  new DataOutputStream (baos);
					
					while (bfr.ready()) {
				
						dos.writeBytes(bfr.readLine()+"\n");
					}
		
					out.putNextEntry(new ZipEntry(name));
		
					out.write(baos.toByteArray());
		
					bfr.close();
					baos.flush();
					baos.close();
					dos.flush();
					dos.close();
			
    			}
		
    			
    			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}finally{
			try {
				if(out != null){
					out.flush();
					out.close();
				}
				if(fos != null){
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    return true;
    }
    
    public static boolean checkFileExist(File folder,String name){
	
    	File f = new File(folder.getAbsolutePath() + File.separator + name);
   	
    	return f.exists();
    	
    }
    
    public static File componeFilePath (File folder,String name){
  
    	return new File(folder.getAbsolutePath() + File.separator + name);
    }

    public static boolean moveFile(File file,File destinationFolder){
    	InputStream in = null;

    	OutputStream out = null;

    	try{
    		if(!destinationFolder.exists())
    			destinationFolder.mkdirs();
    		
    		in = new FileInputStream(file);	
	    	out = new FileOutputStream(destinationFolder+File.separator+file.getName());
	
	    	byte[] buf = new byte[1024];
	
	    	int len;
	
	    	while((len = in.read(buf)) > 0){
	
	    		out.write(buf, 0, len);
	
	    	}

	    	
    	} catch (IOException e) {
			return false;
		}
    	finally{
	    	try {
	    		if(in!=null)
	    			in.close();
				if(out!=null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} 
    	
    	return true;
    	
    	
    }
    
    public static void writeXML(String input,File file){
    	
    	if (input == null || file == null)
    		return;
    	
    	FileOutputStream out 		= null;
    	Transformer transformer 	= null;
    	OutputStreamWriter writer 	= null;
    	StreamResult xmlOutput 		= null;
    	
    	Source xmlInput = new StreamSource(new StringReader(input));
		try {
			out = new FileOutputStream(file);		
			writer = new OutputStreamWriter(out,"UTF-8");
			xmlOutput = new StreamResult(writer);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
     
			try {
				transformer = transformerFactory.newTransformer();
			
		        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");		
		        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		        
		        transformer.transform(xmlInput, xmlOutput);
		        
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}        
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
				try {
					if (out != null){
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
    }
    
	public static File getRootDir(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
          
        return new File(classLoader.getResource("").getFile());

	}
    
    public static boolean moveFile(String file,String destinationFolder){    		
    	return moveFile(new File(file),new File(destinationFolder));
    }
    
    public static boolean deleteFile (File f){    	
    	
    	return f.delete();
    }
    
    public static File inputStreamToFile(InputStream in){
    	 File f=new File("outFile.png");
    	 OutputStream out = null;
		try {
			out = new FileOutputStream(f);
			byte buf[]=new byte[1024];
	    	int len;
	    	while((len=in.read(buf))>0)
	    		  out.write(buf,0,len);
	    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				  try {
					out.close();
					in.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
		    	  
			}
		}
    	  
    	  
    	  return f;
    }
}
