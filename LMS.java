/**************************************************************

Purpose/Description: This Java program simulates a Library
					 Management System (LMS) which allows
					 library staff to manage the collection
					 of books in the library. The staff will
					 have the ability to add, remove, check
 					 out, and check in books, as well as
 					 list/display all books currently in the
 					 collection.

Author’s VID: V03590939

Course: Software Development I CEN-3024C-14835

Last modified: 10/28/2023

Certification: I hereby certify that this work is my own and
			   none of it is the work of any other person.

**************************************************************/

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
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
		System.out.println("3. Check out a book");
		System.out.println("4. Check in a book");
		System.out.println("5. List");
		System.out.println("6. Exit\n");
	}
	
	// This method records the user's input and subsequently
	// calls other methods based on the user's choice.
	public static void getUserInput()
	{
		// Creating a library object.
		Library library1 = new Library();

		Scanner scnr = new Scanner(System.in);
		int userInput;

		// The user is presented with the main menu at least once,
		// and consecutively unless the user chooses to exit.
		// noinspection ConstantValue
		do
		{
			printMenu();
			System.out.print("Enter your selection (integer): ");

			// Catching possible InputMismatchException.
			try
			{
				userInput = scnr.nextInt();
			}
			catch (Exception ex)
			{
				// Clearing the scanner buffer.
				scnr.next();
				// Setting input to 0 to display invalid input message.
				userInput = 0;
			}

            switch (userInput)
			{
                case 1 -> addBooks(library1);
                case 2 -> removeBook(library1);
				// Passing 0 to indicate that the user is choosing to check out a book.
				case 3 -> processBook(library1, 0);
				// Passing 1 to indicate that the user is choosing to check in a book.
				case 4 -> processBook(library1, 1);
				case 5 -> listCollection(library1);
				case 6 ->
				{
					System.out.println("\nGoodbye :)");
					// Exiting successfully.
					System.exit(0);
				}
				default -> System.out.println("\nInvalid input, please enter an integer from 1 to 6,"
											  + " in accordance with your selection.");
            }
		} while (userInput != 6);
	}
	
	// This method obtains a file name from the user, reads in
	// the file, and adds each book entry into the LMS.
	public static void addBooks(Library library)
	{
		Scanner scnr = new Scanner(System.in);
		String fileName = "";
		int numOfBooks = 0;

		// Catching possible FileNotFoundException, InputMismatchException, etc.
		try
		{
			// Obtaining file name from the user.
			System.out.print("\nEnter the name of the text file containing the books, for example, \"books_file.txt\": ");
			fileName = scnr.nextLine();

			FileInputStream inFile = new FileInputStream(fileName);
			
			// The .useDelimiter() method tells the scanner to ignore
			// the ',' and '\n' characters as it reads through the file.
			Scanner fileScnr = new Scanner(inFile).useDelimiter("\\n+|,");

			while (fileScnr.hasNextLine())
			{
				// Creating a new book object.
				Book newBook = new Book();
				
				// Each line in the text file is formatted as follows:
				// <Barcode Number>,<Book Title>,<Book Author>
				// These values are read in and assigned to the new object's attributes.
				newBook.barcodeNum = fileScnr.nextInt();
				newBook.title = fileScnr.next();
				newBook.author = fileScnr.next();

				// Setting the new object's status to "Checked In" and
				// its due date to null as it is just being added to the collection.
				newBook.status = "Checked in";
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

	// This method obtains and returns a book's title or barcode number.
	public static String getTitleOrBarcodeNum(String userChoice)
	{
		Scanner scnr = new Scanner(System.in);
		String userInput;

		System.out.print("\nEnter the book's ");
		switch (userChoice)
		{	// "T" stands for title.
			case "T" -> System.out.print("title: ");
			// "B" stands for barcode number.
			case "B" -> System.out.print("barcode number: ");
		}

		// Catching possible InputMismatchException.
		try
		{
			// Obtaining title or barcode number from the user.
			userInput = scnr.nextLine();
		}
		catch (Exception ex)
		{
			System.out.print("\nInvalid input, failed to retrieve ");
			switch (userChoice)
			{
				case "T" -> System.out.print("title :(");
				case "B" -> System.out.print("barcode number :(");
			}
			// Returning a null statement if the user enters an invalid title or barcode number.
			return null;
		}

		return userInput;
	}

	// This method removes a book from the collection.
	public static void removeBook(Library library)
	{
		// Exiting method if the collection is empty.
		if (library.collection.isEmpty())
		{
			System.out.println("\nThe collection is empty, there are" +
							   " no books to remove at this time.");
			return;
		}

		Scanner scnr = new Scanner(System.in);
		String userChoice, attribute;
		int index = -1;

		System.out.println("\nTo remove a book from the collection you" +
						   " will need to provide its title or barcode number.");
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

		if (userChoice.equalsIgnoreCase("T") ||
			userChoice.equalsIgnoreCase("B"))
			attribute = getTitleOrBarcodeNum(userChoice);
		else
		{
			System.out.println("\nInvalid input, please enter 'T' or" +
							   " 'B' in accordance with your selection.");
			return;
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

	// This method sets the due date when a book is checked out or checked in.
	// It receives a boolean variable that is equivalent to true
	// when checking out or false when checking in a book.
	public static Date setDueDate(Boolean checkingOut)
	{
		Date dueDate;

		if (checkingOut)
		{
			dueDate = new Date();

			// Converting date to calendar.
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.setTime(dueDate);

			// Adding 28 days/4 weeks.
			tempCalendar.add(Calendar.DATE, 28);

			// Assigning added calendar to the due date.
			dueDate = tempCalendar.getTime();
		}
		else
			// If not checking out, the user is checking in a book,
			// therefore the due date will become null.
			dueDate = null;

		return dueDate;
	}

	// This method modifies a book's status and due date when a book is checked out or checked in.
	public static void processBook(Library library, int userChoice)
	{
		// Exiting method if the collection is empty.
		if (library.collection.isEmpty())
		{
			System.out.print("\nThe collection is empty, there are no books to check ");
			switch (userChoice)
			{
				case 0 -> System.out.print("out");
				case 1 -> System.out.print("in");
			}
			System.out.println(" at this time.");

			return;
		}

		// Passing "T" to obtain the title of the book.
		String title = getTitleOrBarcodeNum("T");
		int index = -1;

		// Exiting method if invalid title is entered by the user.
		if (title == null)
			return;

		// Iterating through collection.
		// Use merge sort instead.
		for (Book bookEntry : library.collection)
		{
			// Recording the index of the book if found.
			if (Objects.equals(bookEntry.title, title))
			{
				index = library.collection.indexOf(bookEntry);
				break;
			}
		}

		System.out.print("\nBook with title ");
		// Catching possible IndexOutOfBoundsException.
		try
		{
			switch (userChoice)
			{
				// Checking out book.
				case 0 ->
				{
					library.collection.get(index).status = "Checked out";
					library.collection.get(index).dueDate = setDueDate(true);
				}
				// Checking in book.
				case 1 ->
				{
					library.collection.get(index).status = "Checked in";
					library.collection.get(index).dueDate = setDueDate(false);
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("\"" + title +"\"" + " not found :(");
			return;
		}
		switch (userChoice)
		{
			case 0 -> System.out.println("\"" + title + "\"" + " checked out. It is due by "
										 + library.collection.get(index).dueDate + ".");
			case 1 -> System.out.println("\"" + title +"\"" + " checked in.");
		}
		// Displaying collection after check out/in.
		listCollection(library);
	}

	// This method displays the entire collection.
	public static void listCollection(Library library)
	{
		// Exiting method if the collection is empty.
		if (library.collection.isEmpty())
		{
			System.out.println("\nThe collection is empty, there are" +
							   	" no books to display at this time.");
			return;
		}

		System.out.println("\nListing collection...");
		// Iterating through the ArrayList and printing each book's attributes.
		for (Book bookEntry : library.collection)
		{
			if (bookEntry.status.equals("Checked in") && bookEntry.dueDate == null)
				System.out.println(bookEntry.barcodeNum + ", " + bookEntry.title + ", " + bookEntry.author);
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("\nWelcome to the Library Management System");

		// This method will call other methods and control the program's features.
		getUserInput();
	}
}