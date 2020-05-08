package fr.takebook.library.domain;

import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.domain.model.book.*;

public class LibraryBookFactory {

    public LibraryBook createReturned(LibraryBook book, Library library) {
        LibraryReturnedBook returnedBook = new LibraryReturnedBook();
        merge(book, returnedBook);
        returnedBook.setLibrary(library);
        return returnedBook;
    }

    public LibraryBook createBorrowed(LibraryBook book, LibraryBorrower borrower){
        LibraryBorrowedBook borrowedBook = new LibraryBorrowedBook();
        merge(book, borrowedBook);
        borrowedBook.setBorrower(borrower);
        return borrowedBook;
    }

    public LibraryBook createShelved(LibraryBook book, LibraryShelf shelf) {
        LibraryStoredBook storedBook = new LibraryStoredBook();
        merge(book, storedBook);
        storedBook.setShelf(shelf);
        return storedBook;
    }

    private void merge(LibraryBook from, LibraryBook to) {
        to.setId(from.getId());
        to.setData(from.getData());
    }

}
