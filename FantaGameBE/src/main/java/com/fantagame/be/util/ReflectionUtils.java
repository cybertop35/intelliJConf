package com.fantagame.be.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class ReflectionUtils {
	
	public static final String ALL_CLASS = "all";
	public static final String ONLY_INTERFACE = "interface";
	public static final String ONLY_CLASS = "class";

	@SuppressWarnings("rawtypes")
	public static List<Class> findClasses(String filter, String... packageName ) throws ClassNotFoundException{
		
		List<Class> classes = new ArrayList<Class>();
		for (String _package : packageName){
			String path = _package.replace('.', '/');
			File directory = new File(FileUtil.getRootDir()+File.separator+path);
			classes.addAll(findClasses(directory,filter, _package));
		}
		return classes;
	}
	
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String filter,String packageName) throws ClassNotFoundException 
	{
		
		List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
        	String fileName = file.getName();
            if (file.isDirectory()) {
                assert !fileName.contains(".");
            	classes.addAll(findClasses(file, filter,packageName + "." + fileName));
            } else if (fileName.endsWith(".class") && !fileName.contains("$")) {
            	Class _class;
				try {
					
					_class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
					
				} catch (ExceptionInInitializerError e) {
					_class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
							false, Thread.currentThread().getContextClassLoader());
				}
				if(filter.equalsIgnoreCase(ONLY_CLASS)&& !_class.isInterface())
					classes.add(_class);
				else if (filter.equalsIgnoreCase(ONLY_INTERFACE)&& _class.isInterface())
					classes.add(_class);
				else if (filter.equalsIgnoreCase(ALL_CLASS))
					classes.add(_class);
            }
        }
        return classes;
    }
}
