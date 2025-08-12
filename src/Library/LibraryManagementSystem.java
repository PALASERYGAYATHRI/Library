package Library; // Package declaration

import java.util.Scanner; // Import Scanner for user input

// Main class to run the Library Management System
public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input
        LibraryRepository libraryRepository = new LibraryRepository(); // Create repository object to interact with database

        // Infinite loop to keep the menu running until user exits
        while (true) {
            // Display menu options
            System.out.println("\nLibrary Management System");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Add Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. View All Members");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user's choice
            scanner.nextLine(); // Consume newline character

            // Perform action based on user's choice
            switch (choice) {
                case 1:
                    // View all books in the library
                    libraryRepository.viewAllBooks();
                    break;

                case 2:
                    // Add a new book to the library
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Genere: ");
                    String genere = scanner.nextLine();
                    libraryRepository.addBook(title, author, isbn, genere);
                    break;

                case 3:
                    // Add a new member to the library
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    libraryRepository.addMember(name, email, phone);
                    break;

                case 4:
                    // Record a book borrowing event
                    System.out.print("Member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Borrow Date (YYYY-MM-DD): ");
                    String borrowDate = scanner.nextLine();
                    libraryRepository.borrowBook(memberId, bookId, borrowDate);
                    break;

                case 5:
                    // Record a book return event
                    System.out.print("Member ID: ");
                    int returnMemberId = scanner.nextInt();
                    System.out.print("Book ID: ");
                    int returnBookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Return Date (YYYY-MM-DD): ");
                    String returnDate = scanner.nextLine();
                    libraryRepository.returnBook(returnMemberId, returnBookId, returnDate);
                    break;

                case 6:
                    // View all registered members
                    libraryRepository.viewAllMembers();
                    break;

                case 7:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner
                    libraryRepository.closeConnection(); // Close DB connection
                    System.exit(0); // Terminate program
                    break;

                default:
                    // Handle invalid menu choice
                    System.out.println("Invalid choice.");
            }
        }
    }
}