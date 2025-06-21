package com.service;

import java.io.File;

public class SearchFile {

	private String directoryPath;
	
	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) throws IllegalArgumentException  {
		if(directoryPath == null) {
			throw new IllegalArgumentException("Directory path cannot be null or empty");
		}
		this.directoryPath = directoryPath;
	}
	
	public boolean searchFile(String fileName) {
		File file = new File(directoryPath + File.separator + fileName);
		if(file.exists()) {
			System.out.println("File " + fileName + " found in the directory: " + directoryPath);
			return true;
		} else {
			System.out.println("File " + fileName + " does not exist in the directory: " + directoryPath);
			return false;
		}
	}
	
}
