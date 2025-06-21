package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		File[] files = directory.listFiles();
		if(files == null || files.length == 0) {
			System.out.println("No files found in the directory: " + directoryPath + "\n");
			return;
		} else {
			List<String> fileList = new ArrayList<>();
			for (File file:files) 
				fileList.add(file.getName());
			Collections.sort(fileList);
			System.out.println("Files in directory " + directoryPath + ":");
			for (String file : fileList) {
				System.out.println(file);
			}
		}
	}
	

}
