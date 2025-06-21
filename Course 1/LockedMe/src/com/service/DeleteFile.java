package com.service;

import java.io.File;

public class DeleteFile {
	
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
	
	public void deleteFile(String fileName) {
		File file = new File(directoryPath + File.separator + fileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("File " + fileName + " deleted successfully.");
			} else {
				System.out.println("Failed to delete the file " + fileName + ".");
			}
		} else {
			System.out.println("File " + fileName + " does not exist.");
		}
	}
}
