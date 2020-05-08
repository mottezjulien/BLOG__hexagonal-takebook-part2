package fr.takebook.library.domain;

import fr.takebook.library.domain.LibraryBookFactory;
import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.domain.model.book.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibraryBookFactoryTest {

    private LibraryBookFactory bookFactory = new LibraryBookFactory();

    @Test
    void createBorrowedBook() {
        UUID randomId = UUID.randomUUID();

        LibraryBook book = mock(LibraryBook.class);
        when(book.getId()).thenReturn(randomId);


        LibraryBorrower borrower = mock(LibraryBorrower.class);

        LibraryBook createBook = bookFactory.createBorrowed(book, borrower);
        assertThat(createBook, instanceOf(LibraryBorrowedBook.class));
        LibraryBorrowedBook borrowedBook = (LibraryBorrowedBook) createBook;

        assertEquals(borrowedBook.getId(), randomId);
        assertEquals(borrowedBook.getBorrower(), borrower);

    }

    @Test
    void createReturnedBook() {
        UUID randomId = UUID.randomUUID();
        LibraryBook book = mock(LibraryBook.class);
        when(book.getId()).thenReturn(randomId);

        Library library = new Library();

        LibraryBook createBook = bookFactory.createReturned(book, library);

        assertThat(createBook, instanceOf(LibraryReturnedBook.class));
        LibraryReturnedBook returnedBook = (LibraryReturnedBook) createBook;

        assertEquals(returnedBook.getId(), randomId);
        assertEquals(returnedBook.getLibrary(), library);
    }


    @Test
    void createShelvedBook() {
        UUID randomId = UUID.randomUUID();

        LibraryBook book = mock(LibraryBook.class);

        when(book.getId()).thenReturn(randomId);

        LibraryShelf shelf = new LibraryShelf();
        shelf.setLabel("shelf1");

        LibraryBook createBook = bookFactory.createShelved(book, shelf);

        assertThat(createBook, instanceOf(LibraryStoredBook.class));
        LibraryStoredBook storedBook = (LibraryStoredBook) createBook;

        assertEquals(storedBook.getId(), randomId);
        assertEquals(storedBook.getShelf(), shelf);
    }

}