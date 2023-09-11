/**************************************************************

Purpose/Description: This Java program simulates a Library
					 Management System (LMS) which allows
					 faculty, specifically library staff, to
					 manage the collection of books in the
					 library. The faculty will have the ability
					 to add and remove books, as well as
					 list/display all books currently in the
					 collection.

Author’s VID: V03590939

Certification: I hereby certify that this work is my own and
			   none of it is the work of any other person.

**************************************************************/

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class LMS
{
	// This method prints the main menu.
	public static void printMenu()
	{
		System.out.println("\nWhat would you like to do today?\n");
		System.out.println("1. Add books");
		System.out.println("2. Remove a book");
		System.out.println("3. List");
		System.out.println("4. Exit\n");
	}
	
	// This method records the user's input and subsequently
	// calls other methods based on the user's choice.
	public static void getUserInput()
	{
		ArrayList<Book> collection = new ArrayList<Book>();
		
		int userInput = -1;
		Scanner scnr = new Scanner(System.in);
		
		// The user is presented with the main menu at least once,
		// and consecutively unless the user chooses to exit.
		do
		{
			printMenu();
			System.out.print("Enter your selection: ");
		
			// Catching possible InputMismatchException.
			try
			{
				userInput = scnr.nextInt();
			}
			catch (Exception ex)
			{
				scnr.next();
				userInput = 0;
			}
			
			switch(userInput)
			{
				case 1:
					System.out.println("\nAdding book(s) to the collection...");
					addBooks(collection);
					break;
				case 2:
					removeBook(collection);
					break;
				case 3:
					System.out.println("\nListing collection...");
					listCollection(collection);
					break;
				case 4:
					System.out.println("\nExiting...");
					break;
				default:
					System.out.println("\nInvalid input, please enter a digit from 1 to 4,"
									   + " in accordance with your selection.");
					break;
			}
		} while (userInput != 4);
	}
	
	public static void addBooks(ArrayList<Book> collection)
	{
		// Catching possible FileNotFoundException and InputMismatchException.
		try
		{
			FileInputStream inFile = new FileInputStream("new_books.txt");
			Scanner fileScnr = new Scanner(inFile).useDelimiter("\\n+|,");
			
			while (fileScnr.hasNextLine())
			{
				Book newBook = new Book();
				
				newBook.id = fileScnr.nextInt();
				newBook.title = fileScnr.next();
				newBook.author = fileScnr.next();
				
				collection.add(newBook);
			}
		}
		catch(Exception ex)
		{
			System.out.println("\nFailed to read \"new_books.txt\" file :(");
			System.out.println("Reason: " + ex);
		}
	}
	
	public static void removeBook(ArrayList<Book> collection)
	{
		Scanner scnr = new Scanner(System.in);
		int userInput = -1, index = 0;
		
		System.out.print("\nEnter the ID of the book you would like to remove: ");
		
		try
		{
			userInput = scnr.nextInt();
			
			for (Book bookEntry : collection)
			{
				if (bookEntry.id == userInput)
					index = collection.indexOf(bookEntry);
			}
			
			collection.remove(index);
			System.out.println("\nBook with ID " + userInput + " removed.");
		}
		catch (Exception ex)
		{
			System.out.println("\nInvalid input :(");
			System.out.println("Reason: " + ex);
		}
		
	}
	
	public static void listCollection(ArrayList<Book> collection)
	{
		for (Book bookEntry : collection)
			System.out.println(+ bookEntry.id + ", " + bookEntry.title + ", " + bookEntry.author);
	}
	
	public static void main (String[] args)
	{
		System.out.println("\nWelcome to the Library Management System");
		
		getUserInput();
	}
}