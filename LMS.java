/**************************************************************

Purpose/Description: This Java program simulates a Library
					 Management System (LMS) which allows
					 library staff to manage the collection
					 of books in the library. The staff will
					 have the ability to add and remove books,
					 as well as list/display all books currently
					 in the collection.

Author’s VID: V03590939

Certification: I hereby certify that this work is my own and
			   none of it is the work of any other person.

**************************************************************/

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Scanner;

public class LMS
{
	// This method prints the main menu.
	public static void printMenu()
	{
		System.out.println("\nWhat would you like to do?\n");
		System.out.println("1. Add books");
		System.out.println("2. Remove a book");
		System.out.println("3. List");
		System.out.println("4. Exit\n");
	}
	
	// This method records the user's input and subsequently
	// calls other methods based on the user's choice.
	public static void getUserInput()
	{
		// Creating a library object.
		Library library1 = new Library();
		
		int userInput;
		Scanner scnr = new Scanner(System.in);
		
		// The user is presented with the main menu at least once,
		// and consecutively unless the user chooses to exit.
		//noinspection ConstantValue
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

            switch (userInput)
			{
                case 1 -> addBooks(library1);
                case 2 -> removeBook(library1);
				case 3 -> listCollection(library1);
				case 4 ->
				{
					System.out.println("\nGoodbye :)");
					// Exiting successfully.
					System.exit(0);
				}
				default -> System.out.println("\nInvalid input, please enter a digit from 1 to 4,"
											  + " in accordance with your selection.");
            }
		} while (userInput != 4);
	}
	
	// This method obtains a file name from the user, reads in
	// the file, and adds each book entry into the LMS.
	public static void addBooks(Library library)
	{
		String fileName = "";
		int numOfBooks = 0;

		// Catching possible FileNotFoundException, InputMismatchException, etc.
		try
		{
			// Obtaining file name from the user.
			Scanner scnr = new Scanner(System.in);
			System.out.print("\nEnter the name of the text file containing the books, for example, \"books_file.txt\": ");
			fileName = scnr.nextLine();

			FileInputStream inFile = new FileInputStream(fileName);
			
			// The .useDelimiter() method tells the scanner to ignore
			// the ',' and '\n' characters as it reads through the file.
			Scanner fileScnr = new Scanner(inFile).useDelimiter("\\n+|,");

			while (fileScnr.hasNextLine())
			{
				// Creating a new book instance.
				Book newBook = new Book();
				
				// Each line in the text file is formatted as follows:
				// <Barcode Number>,<Book Title>,<Book Author>
				// These values are read in and assigned to the new object's attributes.
				newBook.barcodeNum = fileScnr.nextInt();
				newBook.title = fileScnr.next();
				newBook.author = fileScnr.next();

				// Setting the new object's status to "Checked In" and
				// its due date to null as it is just being to the collection.
				newBook.status = "Checked In";
				newBook.dueDate = null;
				
				// Adding the new book to the ArrayList.
				library.collection.add(newBook);
				++numOfBooks;
			}

			System.out.println("\n" + numOfBooks + " books added to the collection :)");

			// Perform merge sort here.
		}
		catch(Exception ex)
		{
			System.out.println("\nFailed to read \"" + fileName + "\" file :(");
			System.out.println("Reason: " + ex);
		}
	}

	// This method obtains and returns the book's title.
	public static String getTitle()
	{
		Scanner scnr = new Scanner(System.in);
		String userInput;

		System.out.print("Enter the title of the book you would like to remove: ");

		// Catching possible InputMismatchException.
		try
		{
			// Obtaining title from the user.
			userInput = scnr.nextLine();
		}
		catch (Exception ex)
		{
			System.out.println("\nInvalid input :(");
			// Returning a null statement if the user enters an invalid title.
			return null;
		}

		return userInput;
	}

	// This method obtains and returns the book's barcode number.
	public static String getBarcodeNum()
	{
		Scanner scnr = new Scanner(System.in);
		int userInput;

		System.out.print("Enter the barcode number of the book you would like to remove: ");

		// Catching possible InputMismatchException.
		try
		{
			// Obtaining barcode number from the user.
			userInput = scnr.nextInt();
		}
		catch (Exception ex)
		{
			System.out.println("\nInvalid input :(");
			System.out.println("Please enter an integer.");
			// Returning a null statement if the user enters an invalid barcode number.
			return null;
		}

		return Integer.toString(userInput);
	}

	// This method removes a book from the collection.
	public static void removeBook(Library library)
	{
		// Exiting method if the collection is empty.
		if (library.collection.isEmpty())
		{
			System.out.println("\nThe collection is empty, there are" +
							   " no books to remove at the moment.");
			return;
		}

		Scanner scnr = new Scanner(System.in);
		String userChoice;
		int index = -1;
		String attribute;

		System.out.println("\nTo remove a book from the collection you" +
						   " will need to provide a title or barcode number.");
		System.out.print("Enter 'T' for title or 'B' for barcode number: ");

		// Catching possible InputMismatchException.
		try
		{
			userChoice = scnr.nextLine();
		}
		catch (Exception ex)
		{
			System.out.println("Invalid input :(");
			return;
		}

		// Converting the user's input to upper case.
		userChoice = userChoice.toUpperCase();

		switch (userChoice)
		{
			case "T" -> attribute = getTitle();
			case "B" -> attribute = getBarcodeNum();
			default ->
			{
				System.out.println("\nInvalid input, please enter 'T' or" +
								   " 'B' in accordance with your selection.");
				return;
			}
		}

		// Exiting method if invalid attribute is entered by the user.
		if (attribute == null)
			return;

		// Iterating through collection.
		// Use merge sort instead.
		for (Book bookEntry : library.collection)
		{
			if (userChoice.equals("T"))
			{
				// Recording the index of the book if found.
				if (Objects.equals(bookEntry.title, attribute))
				{
					index = library.collection.indexOf(bookEntry);
					break;
				}
			}
			if (userChoice.equals("B"))
			{
				// Recording the index of the book if found.
				if (Objects.equals(Integer.toString(bookEntry.barcodeNum), attribute))
				{
					index = library.collection.indexOf(bookEntry);
					break;
				}
			}
		}

		System.out.print("\nBook with ");
		if (userChoice.equals("T"))
			System.out.print("title ");
		if (userChoice.equals("B"))
			System.out.print("barcode number ");
		// Catching possible IndexOutOfBoundsException.
		try
		{
			// Removing the book from the ArrayList.
			library.collection.remove(index);
		}
		catch (Exception ex)
		{
			System.out.println("\"" + attribute +"\"" + " not found :(");
			return;
		}
		System.out.println("\"" + attribute +"\"" + " removed.");

		// Displaying collection after removal.
		listCollection(library);
	}
	
	// This method displays the entire collection.
	public static void listCollection(Library library)
	{
		// Exiting method if the collection is empty.
		if (library.collection.isEmpty())
		{
			System.out.println("\nThe collection is empty, there are" +
							   	" no books to display at the moment.");
			return;
		}

		System.out.println("\nListing collection...");
		// Iterating through the ArrayList and printing each book's attributes.
		for (Book bookEntry : library.collection)
			System.out.println(bookEntry.barcodeNum + ", " + bookEntry.title + ", " + bookEntry.author);
	}
	
	public static void main(String[] args)
	{
		System.out.println("\nWelcome to the Library Management System");

		getUserInput();
	}
}