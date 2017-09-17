package com.fantagame.be.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;

//import com.fantagame.be.ws.model.MessageGroup;



public class XSDGenerator {    

	public static final int FILE = 1;
	public static final int STRING = 2;
	
	public XSDGenerator() {    
		
	}

	class FileOutputResolver extends SchemaOutputResolver {            
	   	File baseDir = new File(".");
	   	File f = null;
        public Result createOutput(String namespaceUri,String suggestedFileName) throws IOException {   
        	f = new File(baseDir, suggestedFileName);
        	return new StreamResult(f);            
	    } 
        public String getFilePath(){
        	return f.getAbsolutePath();
        }
	 }
	
	class StringOutputResolver extends SchemaOutputResolver {            
	   	File baseDir = new File(".");
	   	Writer writer = null;
        public Result createOutput(String namespaceUri,String suggestedFileName) throws IOException {
        	writer = new StringWriter();
        	return new StreamResult(writer);            
	    }
        public String getSchemaString(){
        	return writer.toString() ;
        }
	 }
 
	class StringResult {

	}
    public String generateSchema(int resultType,Class<?>... classesToBeBound){
    	JAXBContext context = null;
		
    	try {
			context = JAXBContext.newInstance(classesToBeBound);
		} catch (JAXBException e1) {		 
			e1.printStackTrace();
		}
		
    	String reString = null;
    	
    	try {
	    	switch(resultType){
	    		case FILE:	    	
	    			FileOutputResolver fileOutputResolver = new FileOutputResolver();
					context.generateSchema(fileOutputResolver);
					reString = fileOutputResolver.getFilePath();
					break;
	    		case STRING:
	    			StringOutputResolver stringOutputResolver = new StringOutputResolver();
					context.generateSchema(stringOutputResolver);
					reString = stringOutputResolver.getSchemaString();
				break;
	    	}
    	} catch (IOException e) {
    		e.printStackTrace();
		}
    	return reString;
    }

    public String javaToXml(int resultType,File schema,Object obj,Class<?> classesToBeBound){
    	 JAXBContext context;
         String reString = null;
		try {
			context = JAXBContext.newInstance(classesToBeBound);
			
	    	 Marshaller marshaller = context.createMarshaller();
	    	 
	    	 if(schema != null && schema.exists())
	    		 marshaller.setSchema(createSchema(schema));
	        
	    	 marshaller.setProperty (Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    	 
	         switch(resultType){
	 			case FILE:	    	
	 			FileOutputResolver fileOutputResolver = new FileOutputResolver();
	 			 	marshaller.marshal(obj,(Result) fileOutputResolver);
					reString = fileOutputResolver.getFilePath();
	 			case STRING:
	 				StringWriter stringOutputResolver = new StringWriter();
	 				marshaller.marshal(obj,stringOutputResolver);
					reString = stringOutputResolver.toString();
				
	         }
		} catch (JAXBException e) {
			
			e.printStackTrace();
		}
        
         return reString;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T xmlToJava(File schema,String obj,Class<?> classesToBeBound){
   	 JAXBContext context;
        T reString = null;
		try {
			context = JAXBContext.newInstance(classesToBeBound);
		
	    	 Unmarshaller unmarshaller = context.createUnmarshaller();
	    	 
	    	 if(schema != null && schema.exists())
	    		 unmarshaller.setSchema(createSchema(schema));
	    	 reString = (T) unmarshaller.unmarshal(new StringReader(obj));
	    	 
	    	 
	    	 	
		} catch (JAXBException e) {
			
			e.printStackTrace();
		}
       
        return reString;
   }
    
    private Schema createSchema(File schema){
    	// SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

 		return null; 
    }
    
} 