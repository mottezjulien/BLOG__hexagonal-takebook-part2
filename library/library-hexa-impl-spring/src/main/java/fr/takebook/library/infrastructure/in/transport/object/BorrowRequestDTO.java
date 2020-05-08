package fr.takebook.library.infrastructure.in.transport.object;

public class BorrowRequestDTO {

    private LibraryBookDTO book;

    private LibraryBorrowerDTO borrower;

    public LibraryBookDTO getBook() {
        return book;
    }

    public void setBook(LibraryBookDTO book) {
        this.book = book;
    }

    public LibraryBorrowerDTO getBorrower() {
        return borrower;
    }

    public void setBorrower(LibraryBorrowerDTO borrower) {
        this.borrower = borrower;
    }
}
