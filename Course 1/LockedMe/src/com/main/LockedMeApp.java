package com.main;

import java.io.File;
import java.util.Scanner;

public class LockedMeApp {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		File directory = new File("LockedMeApp");
		boolean directoryCreated = directory.mkdir();
		
//		if(directoryCreated)
//			System.out.println("New directory created");
//		else
//			System.out.println("Directory already exists. Using existing directory");

		System.out.println("App Name: LockedMe");
		System.out.println("Developed by: Vedant Verma");
		System.out.println("Organization: Company Lockers Pvt. Ltd. ");
		
		while(true) {
			
			int choice = scanner.nextInt();
			
			System.out.println("Choose one of the following options as required: ");
			System.out.println("1. Display all files in an ascending order.");
			System.out.println("2. Add/Search/Delete a file.");
			System.out.println("3. Exit the application.");
			
			switch(choice) {
			
			case 1: break;
			case 2: break;
			case 3: System.out.println("Exited the application.");
					return;
			default: System.out.println("Enter a valid choice...\n");
			
			
			}
		}
		
		
		
		
		
		
	}

}
