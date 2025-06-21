package com.main;

import java.io.File;
import java.util.Scanner;

import com.service.DisplayFiles;


public class LockedMeApp {

	private static Scanner scanner = new Scanner(System.in);
	private static final String DIRECTORY_PATH = "LockedMeApp";
	private static final DisplayFiles displayFiles = new DisplayFiles();
	
	public static void main(String[] args) {
		
		File directory = new File(DIRECTORY_PATH);
		boolean directoryCreated = directory.mkdirs();
		
		
		displayFiles.setDirectoryPath(DIRECTORY_PATH);
		
//		if(directoryCreated)
//			System.out.println("New directory created");
//		else
//			System.out.println("Directory already exists. Using existing directory");

		System.out.println("App Name: LockedMe");
		System.out.println("Developed by: Vedant Verma");
		System.out.println("Organization: Company Lockers Pvt. Ltd. ");
		
		while(true) {
			
			System.out.println("Choose one of the following options as required: ");
			System.out.println("1. Display all files in an ascending order.");
			System.out.println("2. Add/Search/Delete a file.");
			System.out.println("3. Exit the application.");
			
			int choice = scanner.nextInt();
			
			switch(choice) {
			
			case 1: displayFiles.displayFilesInAscendingOrder();
				break;
			case 2: while(true) {
				System.out.println("CRUD operations menu:");
				System.out.println("1. Add a file");
				System.out.println("2. Delete a file");
				System.out.println("3. Search a file");
				System.out.println("4. Go back to main menu");
				System.out.println("Enter your choice: ");

				int choice1 = scanner.nextInt();
				
				switch(choice1) {
					case 1: 
						System.out.println("Enter the name of the file to add:");
						String addFileName = scanner.next();
						// Code to add file
						System.out.println("File " + addFileName + " added successfully.");
						break;
					case 2: 
						System.out.println("Enter the name of the file to delete:");
						String deleteFileName = scanner.next();
						// Code to delete file
						System.out.println("File " + deleteFileName + " deleted successfully.");
						break;
					case 3: 
						System.out.println("Enter the name of the file to search:");
						String searchFileName = scanner.next();
						// Code to search file
						System.out.println("File " + searchFileName + " found.");
						break;
					case 4: 
						break; // Go back to main menu
					default: 
						System.out.println("Invalid choice. Please try again.\n");
				}
			}
			case 3: System.out.println("Exited the application.");
					return;
			default: System.out.println("Enter a valid choice...\n");
			
			}
		}
	}
}
