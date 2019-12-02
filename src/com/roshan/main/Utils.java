package com.roshan.main;

public class Utils {

	public static String getNameWithoutExtension(String fileName){
		 int dotIndex = fileName.lastIndexOf('.');
		 return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
	}
	
}
