package com.fantagame.be.application;

import java.io.File;


import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class LoadResources implements ResourceLoaderAware {
	
	private File pathImg;
	private File pathLogoSquadra;
	private File pathTemplateMail;
	private ResourceLoader resourceLoader;
	
	private LoadResources(){}
	
 	public void setPathImg(File pathImg) {
		this.pathImg = pathImg;
	}
	
	public void setPathLogoSquadra(File pathLogoSquadra) {
		this.pathLogoSquadra = pathLogoSquadra;
	}

	public void setPathTemplateMail(File pathTemplateMail) {
		this.pathTemplateMail = pathTemplateMail;
	}

	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		
	}	
		
	public Resource getImg(String name){
		return resourceLoader.getResource(pathImg.getAbsolutePath()+File.separator+name);
	}

	public Resource getLogo(String name){
		return searchLogo(name);
	}

	private Resource searchLogo(String name){
		
			//File  folder = resourceLoader.getResource(pathLogoSquadra.getAbsolutePath()).getFile();
			File [] list = pathLogoSquadra.listFiles();
			for(File f : list)
				if(f.getName().toUpperCase().contains(name.toUpperCase()))
					return resourceLoader.getResource("file:"+f.getAbsolutePath());
		
		
		return null;
	}
	
	public String getTemplateMail(String template){
		return pathTemplateMail.getAbsolutePath()+File.separator+template;
	}
	
}
