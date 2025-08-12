package Library; // Package declaration
 
// Class to represent a record of a borrowed book
public class BorrowRecord {
    // Member ID who borrowed the book
    private int memberId;

    // Book ID that was borrowed
    private int bookId;

    // Date when the book was borrowed
    private String borrowDate;

    // Date when the book was returned (can be null if not yet returned)
    private String returnDate;

    // Constructor to initialize all fields of the borrow record
    public BorrowRecord(int memberId, int bookId, String borrowDate, String returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getter for member ID
    public int getMemberId() { return memberId; }

    // Getter for book ID
    public int getBookId() { return bookId; }

    // Getter for borrow date
    public String getBorrowDate() { return borrowDate; }

    // Getter for return date
    public String getReturnDate() { return returnDate; }

    // Setter to update the return date
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}