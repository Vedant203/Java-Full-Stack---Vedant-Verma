package com.service;

import java.io.File;

public class DisplayFiles{
	
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
	
	public void displayFilesInAscendingOrder() {
		
		File directory = new File(directoryPath);
		String[] files = directory.list();
		if(files == null || files.length == 0) {
			System.out.println("No files found in the directory: " + directoryPath + "\n");
			return;
		} else {
			System.out.println("Files in directory " + directoryPath + ":");
			for (String file : files) {
				System.out.println(file);
			}
		}
	}
	

}
