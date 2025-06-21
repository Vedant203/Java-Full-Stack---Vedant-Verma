package com.service;

import java.io.File;

public class CreateFile {
	
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
	
	public void addFile(String fileName) {
		File file = new File(directoryPath + File.separator + fileName);
		try {
			if(file.createNewFile()) {
				System.out.println("File " + fileName + " created successfully.");
			} else {
				System.out.println("File " + fileName + " already exists.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while creating the file: " + e.getMessage());
		}
	}
}
