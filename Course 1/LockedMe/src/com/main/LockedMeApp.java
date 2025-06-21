package com.main;

import java.io.File;
import java.util.Scanner;

import com.service.CreateFile;
import com.service.DeleteFile;
import com.service.DisplayFiles;
import com.service.SearchFile;


public class LockedMeApp {

	private static Scanner scanner = new Scanner(System.in);
	private static final String DIRECTORY_PATH = "LockedMeApp";
	private static final DisplayFiles displayFiles = new DisplayFiles();
	private static final CreateFile createFile = new CreateFile();
	private static final DeleteFile deleteFile = new DeleteFile();
	private static final SearchFile searchFile = new SearchFile();
	
	public static void main(String[] args) {
		
		File directory = new File(DIRECTORY_PATH);
		boolean directoryCreated = directory.mkdirs();
		
		displayFiles.setDirectoryPath(DIRECTORY_PATH);
		createFile.setDirectoryPath(DIRECTORY_PATH);
		deleteFile.setDirectoryPath(DIRECTORY_PATH);
		searchFile.setDirectoryPath(DIRECTORY_PATH);
//		if(directoryCreated)
//			System.out.println("New directory created");
//		else
//			System.out.println("Directory already exists. Using existing directory");

		System.out.println("App Name: LockedMe");
		System.out.println("Developed by: Vedant Verma");
		System.out.println("Organization: Company Lockers Pvt. Ltd. ");
		
		while(true) {
			System.out.println();
			System.out.println("Choose the operation you want to perform:");
			System.out.println("1. Display all files in an ascending order.");
			System.out.println("2. Add/Search/Delete a file.");
			System.out.println("3. Exit the application.");
			System.out.println("Enter your choice: ");
			
			
			int choice = scanner.nextInt();
			
			switch(choice) {
			
			case 1: displayFiles.displayFilesInAscendingOrder();
				break;
			case 2: crudOperationsOptions();
				break;
			case 3: System.out.println("Exited the application.");
				return;
			default: System.out.println("Enter a valid choice...\n");
			
			}
		}
	}
	public static void crudOperationsOptions() {
		while(true) {
			System.out.println();
			System.out.println("CRUD operations menu:");
			System.out.println("1. Add a file");
			System.out.println("2. Delete a file");
			System.out.println("3. Search a file");
			System.out.println("4. Go back to main menu");
			System.out.println("Enter your choice: ");
			
			int choice1;
			try{choice1 = scanner.nextInt();}
			catch(Exception e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.next(); // Clear the invalid input
				continue; // Restart the loop
			}
			switch(choice1) {
				case 1: 
					System.out.println("Enter the name of the file to add:");
					String addFileName = scanner.next();
					createFile.addFile(addFileName);
					break;
				case 2: 
					System.out.println("Enter the name of the file to delete:");
					String deleteFileName = scanner.next();
					deleteFile.deleteFile(deleteFileName);
					break;
				case 3: 
					System.out.println("Enter the name of the file to search:");
					String searchFileName = scanner.next();
					searchFile.searchFile(searchFileName);
					break;
				case 4: 
					return;
				default: 
					System.out.println("Invalid choice. Please try again...\n");
			}
		}
	}
}
