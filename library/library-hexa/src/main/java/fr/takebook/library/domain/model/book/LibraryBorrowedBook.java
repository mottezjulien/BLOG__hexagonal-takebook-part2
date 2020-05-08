package fr.takebook.library.domain.model.book;

public class LibraryBorrowedBook extends LibraryBook {

    private LibraryBorrower borrower;

    public void setBorrower(LibraryBorrower borrower) {
        this.borrower = borrower;
    }

    public LibraryBorrower getBorrower() {
        return borrower;
    }

    @Override
    public LibraryBookState getState() {
        return LibraryBookState.BORROWED;
    }

}
