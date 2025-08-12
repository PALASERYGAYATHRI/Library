package Library;// Package declaration


import java.sql.*;

public class LibraryRepository {

    // Connection object to interact with the database
    private Connection conn;

    // Constructor: Establishes connection to the MySQL database
    public LibraryRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", // Database URL
                "root",                                // Username
                "gayathri@2006"                        // Password
            );
            System.out.println(" Connected to MySQL database.");
        } catch (Exception e) {
            System.out.println("Failed to connect to MySQL.");
            e.printStackTrace();
        }
    }

    // Adds a new book to the 'books' table
    public void addBook(String title, String author, String isbn, String genere) {
        String query = "INSERT INTO books (title, author, isbn, genere) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, genere);
            stmt.executeUpdate();
            System.out.println("📚 Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adds a new member to the 'members' table
    public void addMember(String name, String email, String phone) {
        String query = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            System.out.println("👤 Member added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Records a book borrowing event in the 'borrow_records' table
    public void borrowBook(int memberId, int bookId, String borrowDate) {
        String query = "INSERT INTO borrow_records (member_id, book_id, borrow_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            stmt.setInt(2, bookId);
            stmt.setString(3, borrowDate);
            stmt.executeUpdate();
            System.out.println("📖 Book borrowed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates the return date for a borrowed book in the 'borrow_records' table
    public void returnBook(int memberId, int bookId, String returnDate) {
        String query = "UPDATE borrow_records SET return_date = ? WHERE member_id = ? AND book_id = ? AND return_date IS NULL";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, returnDate);
            stmt.setInt(2, memberId);
            stmt.setInt(3, bookId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" Book returned successfully.");
            } else {
                System.out.println("No matching borrow record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Displays all books in a formatted table
    public void viewAllBooks() {
        String query = "SELECT * FROM books";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultset = stmt.executeQuery()) {

            // Header for book list
            System.out.println("Books:");
            System.out.println("+--------+---------------------------------------+---------------------+---------------+--------------------+");
            System.out.println("| Book ID| Title                                 | Author              | ISBN          | Genere             |");
            System.out.println("+--------+---------------------------------------+---------------------+---------------+--------------------+");

            // Iterate through result set and print each book's details
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String title = resultset.getString("title");
                String author = resultset.getString("author");
                String isbn = resultset.getString("isbn");
                String genere = resultset.getString("genere");

                // Print formatted book info
                System.out.printf("| %-7d| %-28s| %-20s| %-12s| %-15s|\n", id, title, author, isbn, genere);
                System.out.println("+--------+---------------------------------------+---------------------+---------------+--------------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Displays all members in a formatted table
    public void viewAllMembers() {
        String query = "SELECT * FROM members";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet resultset = stmt.executeQuery()) {

            // Header for member list
            System.out.println("Members:");
            System.out.println("+-----------+--------------------------+--------------------------+--------------+");
            System.out.println("| Member ID | Name                     | Email                    | Phone         |");
            System.out.println("+-----------+--------------------------+--------------------------+--------------+");

            // Iterate through result set and print each member's details
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                String email = resultset.getString("email");
                String phone = resultset.getString("phone");

                // Print formatted member info
                System.out.printf("| %-10d| %-25s| %-25s| %-12s|\n", id, name, email, phone);
                System.out.println("+-----------+--------------------------+--------------------------+--------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Closes the database connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println(" Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}