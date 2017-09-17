package com.cybertop.tools.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

public class GroupBy {

	private String sourcePath;
	private String destinationPath;
	private Groupping[] groupping;
	private boolean isMove;
	private File destination;
	
	public GroupBy(String sourcePath, String destinationPath,
			Groupping[] groupping, boolean isMove) {
		super();
		this.sourcePath = sourcePath;
		this.destinationPath = destinationPath;
		this.groupping = groupping;
		this.isMove = isMove;
		
		if(!checkGroupping(groupping))
			throw new IllegalArgumentException("Groupping not valid");
		
		destination = new File(destinationPath);
		
		if(!destination.exists())
			destination.mkdirs();
	}
	
	private boolean checkGroupping(Groupping[] groupping2) {
		if(groupBy(Groupping.ALBUM) && !groupBy(Groupping.AUTOR) && !groupBy(Groupping.GENERE) && !groupBy(Groupping.YEAR))
			return true;
		else if(!groupBy(Groupping.ALBUM) && groupBy(Groupping.AUTOR) && !groupBy(Groupping.GENERE) && !groupBy(Groupping.YEAR))
			return true;
		else if(!groupBy(Groupping.ALBUM) && !groupBy(Groupping.AUTOR) && groupBy(Groupping.GENERE) && !groupBy(Groupping.YEAR))
			return true;
		else if (!groupBy(Groupping.ALBUM) && !groupBy(Groupping.AUTOR) && !groupBy(Groupping.GENERE) && groupBy(Groupping.YEAR))
			return true;
		else if(groupBy(Groupping.ALBUM) && groupBy(Groupping.AUTOR) && !groupBy(Groupping.GENERE) && !groupBy(Groupping.YEAR))
			return true;
		else if(!groupBy(Groupping.ALBUM) && !groupBy(Groupping.AUTOR) && groupBy(Groupping.GENERE) && groupBy(Groupping.YEAR))
			return true;
		else if(!groupBy(Groupping.ALBUM) && groupBy(Groupping.AUTOR) && !groupBy(Groupping.GENERE) && groupBy(Groupping.YEAR))
			return true;
		else 
			return false;
		
	}

	public void execute(){
		read(sourcePath);
	}
	
	private void read(String path){
		File source = new File(path);
		File files [] = source.listFiles();
		
		for(File f : files){
			if(f.isDirectory())
				read(f.getAbsolutePath());
			else{
				try {
					MP3File mp3 = new MP3File(f);
					String subPath = getSubPath(mp3);
					File subPathFile = new File(subPath);
					
					if(!subPathFile.exists())
						subPathFile.mkdirs();					

					moveOrCopyFile(f, subPathFile,getTitle(mp3),isMove,false);				
				
					
				} catch (IOException e) {					
					System.err.println("Error copy "+f.getAbsolutePath());
				} catch (Exception e) {					
					System.err.println("Error copy "+f.getAbsolutePath() +" message "+e.getMessage());
				}
			}
		}
	}
	
	private void moveOrCopyFile(File source,File destination,String title,boolean delete,boolean rename) {
		
		FileOutputStream out = null;
		FileInputStream in = null;
		try {
			String newTitle = source.getName();
			
			if(rename){
				if((title != null && title.length() > 0))
					newTitle = title+".mp3";
			}
			
			out = new FileOutputStream(destination.getAbsolutePath()+File.separatorChar+newTitle);
			in = new FileInputStream(source);
			
			byte[] buffer = new byte[1024];
			 
		    int length;
		    //copy the file content in bytes 
		    while ((length = in.read(buffer)) > 0){
		    	out.write(buffer, 0, length);
		    }
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{	
			 try {
				 in.close();
				 out.close();
				 out.flush();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("File is copied successful!--- >" +source.getName());
		if(delete)
			source.delete();
	
	}
	
	private String getTitle(MP3File mp3){
		if(mp3.getID3v1Tag() != null ){
			return mp3.getID3v1Tag().getTitle();
		}
		else if(mp3.getID3v2Tag() != null){
			return mp3.getID3v2Tag().getSongTitle();
		}
		else
			return null;
	}
	
	private String getSubPath(MP3File mp3){
		String album = null;
		String autor = null;
		String genere = null;
		String year = null;
		
		if(mp3.getID3v1Tag() != null ){
			album = mp3.getID3v1Tag().getAlbumTitle();
			autor = mp3.getID3v1Tag().getLeadArtist();
			genere = mp3.getID3v1Tag().getSongGenre();
			year = mp3.getID3v1Tag().getYearReleased();
		}
		else if(mp3.getID3v2Tag() != null){
			album = mp3.getID3v2Tag().getAlbumTitle();
			autor = mp3.getID3v2Tag().getLeadArtist();
			genere = mp3.getID3v2Tag().getSongGenre();
			year = mp3.getID3v2Tag().getYearReleased();
		}
		
		StringBuilder path = new StringBuilder();
		path.append(destinationPath);
		
		for(Groupping g : groupping){
			path.append(File.separatorChar);
			if(g.equals(Groupping.ALBUM) && album != null && album.length() > 0){				
				path.append(album);
			}
			else if(g.equals(Groupping.AUTOR) && autor != null && autor.length() > 0){				
				path.append(autor);
			}
			else if(g.equals(Groupping.GENERE) && genere  != null && genere.length() > 0){				
				path.append(genere);				
			}
			else if(g.equals(Groupping.YEAR) && year != null && year.length() > 0){				
				path.append(year);				
			}
			else{
				path.append("vari");
			}
		}
		path.append(File.separatorChar);
		
		return path.toString();
	}

	private boolean groupBy(Groupping group) {
		for(Groupping g : groupping)
			if(g.equals(group))
				return true;
		
		return false;
	}
}
