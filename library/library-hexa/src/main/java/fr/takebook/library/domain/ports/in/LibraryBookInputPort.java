package fr.takebook.library.domain.ports.in;

import fr.takebook.library.domain.LibraryBookFactory;
import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.book.LibraryBorrower;
import fr.takebook.library.domain.ports.out.LibraryBookOutputPort;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.domain.model.book.LibraryBook;


import java.util.UUID;
import java.util.stream.Stream;

public class LibraryBookInputPort {

    private LibraryBookOutputPort outputPort;

    private LibraryBookFactory bookFactory = new LibraryBookFactory();

    public LibraryBookInputPort(LibraryBookOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    LibraryBookInputPort(LibraryBookOutputPort outputPort, LibraryBookFactory bookFactory) {
        this.outputPort = outputPort;
        this.bookFactory = bookFactory;
    }

    public LibraryBook borrowBook(LibraryBook book, LibraryBorrower borrower) throws LibraryBookInputException {
        LibraryBook foundBook = findById(book.getId());
        if (foundBook.isStored()) {
            LibraryBook borrowed = bookFactory.createBorrowed(foundBook, borrower);
            outputPort.update(borrowed);
            return borrowed;
        }
        throw new LibraryBookInputException("The book is not shelved !");
    }

    public LibraryBook returnBook(LibraryBook book, Library library) throws LibraryBookInputException {
        LibraryBook foundBook = findById(book.getId());
        if (foundBook.isBorrowed()) {
            LibraryBook returned = bookFactory.createReturned(foundBook, library);
            outputPort.update(returned);
            return returned;
        }
        throw new LibraryBookInputException("The book is not borrowed !");
    }

    public LibraryBook putAwayBook(LibraryBook book, LibraryShelf shelf) throws LibraryBookInputException {
        LibraryBook foundBook = findById(book.getId());
        if (foundBook.isReturned()) {
            LibraryBook shelved = bookFactory.createShelved(foundBook, shelf);
            outputPort.update(shelved);
            return shelved;
        }
        throw new LibraryBookInputException("The book is not returned !");
    }

    private LibraryBook findById(UUID id) throws LibraryBookInputException {
        return outputPort
                .findById(id)
                .orElseThrow(() -> new LibraryBookInputException("The book is not found in the repository !"));
    }

    public Stream<LibraryBook> findAll() {
        return outputPort.findAll();
    }

    public Stream<LibraryBook> findByBorrower(LibraryBorrower borrower) {
        return outputPort.findByBorrower(borrower);
    }
}
