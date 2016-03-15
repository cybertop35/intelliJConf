package com.fantagame.be.business.data.dataload;

import java.io.File;
import java.util.HashMap;

import com.fantagame.be.business.data.dataload.IFace.IDataLoad;
import com.fantagame.be.util.FileUtil;

public class CheckLoad {

	private File sourceFolder;
	private File destinationFolder;
	
	private boolean useSGRFile;
	
	private HashMap<String,String> fileNameMap;
	private HashMap<String,IDataLoad<?, ?>> dataloadMap;
	
	public static final String SQUADRA = "S";

	public static final String RUOLO = "R";
	public static final String GIOCATORE = "G";
	
	public static final String CALENDARIO = "C";
	public static final String ANDAMENTO = "A";
	
	public  static final String SGR = "SGR";
	
	public CheckLoad(){
		super();
	}
	
	public File getSourceFolder() {
		return sourceFolder;
	}
	public void setSourceFolder(File pathSourceFolder) {
		this.sourceFolder = pathSourceFolder;
	}
	
	public File getDestinationFolder() {
		return destinationFolder;
	}
	public void setDestinationFolder(File destinationFolder) {
		this.destinationFolder = destinationFolder;
	}
	
	public boolean isSGRFile() {
		return useSGRFile;
	}
	public void setSGRFile(boolean sGRFile) {
		useSGRFile = sGRFile;
	}
	
	public HashMap<String, String> getFileNameMap() {
		return fileNameMap;
	}
	public void setFileNameMap(HashMap<String, String> fileNameMap) {
		this.fileNameMap = fileNameMap;
	}
	
	public HashMap<String, IDataLoad<?, ?>> getDataloadMap() {
		return dataloadMap;
	}
	public void setDataloadMap(HashMap<String, IDataLoad<?, ?>> dataloadMap) {
		this.dataloadMap = dataloadMap;
	}
	
	public void check(){
		if(!checkInvalidInput())
			throw new IllegalArgumentException("Invalid Input");
		
		//File sourceFolder = new File(pathSourceFolder);
		
		
		if(!sourceFolder.isDirectory())
			throw new IllegalArgumentException("Invalid source folder");
	

		for(String Key :fileNameMap.keySet()){
			if(FileUtil.checkFileExist(sourceFolder,fileNameMap.get(Key))){
				File f = FileUtil.componeFilePath(sourceFolder, fileNameMap.get(Key));
				dataloadMap.get(Key).setUrl(f.getAbsolutePath());
				dataloadMap.get(Key).load();
				if(FileUtil.moveFile(f, destinationFolder))
					f.delete();
					
			}
		}
	}
	
	private boolean checkInvalidInput() {
		if(sourceFolder == null)
			return false;
		if(fileNameMap == null)
			return false;
		if(dataloadMap == null)
			return false;
		
		
		return true;
	}
	
	
	
}
