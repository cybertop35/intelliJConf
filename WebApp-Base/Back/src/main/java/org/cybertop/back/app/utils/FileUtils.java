package org.cybertop.back.app.utils;

import java.io.InputStream;

public class FileUtils {

	public static InputStream getResources(String resourcename){
		InputStream stream = FileUtils.class.getResourceAsStream("/"+resourcename);
		return stream;
	}
}
