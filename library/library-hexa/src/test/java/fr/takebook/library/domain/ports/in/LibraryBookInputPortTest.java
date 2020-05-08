package fr.takebook.library.domain.ports.in;

import fr.takebook.library.domain.LibraryBookFactory;
import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.domain.model.book.LibraryBook;
import fr.takebook.library.domain.model.book.LibraryBorrower;
import fr.takebook.library.domain.ports.out.LibraryBookOutputPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LibraryBookInputPortTest {

    private LibraryBookOutputPort outputPort = mock(LibraryBookOutputPort.class);
    
    private LibraryBookFactory bookFactory = mock(LibraryBookFactory.class);

    private LibraryBookInputPort inputPort = new LibraryBookInputPort(outputPort, bookFactory);

    @Test
    void borrowIfShelved() throws LibraryBookInputException {
        LibraryBook shelved = mock(LibraryBook.class);
        UUID shelvedId = UUID.randomUUID();
        when(shelved.getId()).thenReturn(shelvedId);
        when(shelved.isStored()).thenReturn(true);

        LibraryBook borrowed = mock(LibraryBook.class);
        LibraryBorrower borrower = mock(LibraryBorrower.class);

        when(outputPort.findById(shelvedId)).thenReturn(Optional.of(shelved));
        when(bookFactory.createBorrowed(shelved, borrower)).thenReturn(borrowed);

        LibraryBook response = inputPort.borrowBook(shelved, borrower);
        assertEquals(response, borrowed);
        verify(outputPort).update(borrowed);
    }


    @Test
    void notBorrowIfNotShelved() {
        LibraryBook shelved = mock(LibraryBook.class);
        UUID shelvedId = UUID.randomUUID();
        when(shelved.getId()).thenReturn(shelvedId);
        when(shelved.isStored()).thenReturn(false);

        LibraryBook borrowed = mock(LibraryBook.class);
        LibraryBorrower borrower = mock(LibraryBorrower.class);

        when(outputPort.findById(shelvedId)).thenReturn(Optional.of(shelved));
        when(bookFactory.createBorrowed(shelved, borrower)).thenReturn(borrowed);

        assertThrows(LibraryBookInputException.class, () -> inputPort.borrowBook(shelved, borrower));
    }


    @Test
    void returnBookIfBorrowed() throws LibraryBookInputException {
        LibraryBook returned = mock(LibraryBook.class);

        LibraryBook borrowed = mock(LibraryBook.class);
        UUID borrowedId = UUID.randomUUID();
        when(borrowed.getId()).thenReturn(borrowedId);
        when(borrowed.isBorrowed()).thenReturn(true);

        Library library = mock(Library.class);

        when(outputPort.findById(borrowedId)).thenReturn(Optional.of(borrowed));
        when(bookFactory.createReturned(borrowed, library)).thenReturn(returned);

        LibraryBook response = inputPort.returnBook(borrowed, library);
        assertEquals(response, returned);
        verify(outputPort).update(returned);
    }


    @Test
    void notReturnBookIfNotBorrowed() {
        LibraryBook returned = mock(LibraryBook.class);

        LibraryBook borrowed = mock(LibraryBook.class);
        UUID borrowedId = UUID.randomUUID();
        when(borrowed.getId()).thenReturn(borrowedId);
        when(borrowed.isBorrowed()).thenReturn(false);

        Library library = mock(Library.class);

        when(outputPort.findById(borrowedId)).thenReturn(Optional.of(borrowed));
        when(bookFactory.createReturned(borrowed, library)).thenReturn(returned);

        assertThrows(LibraryBookInputException.class, () -> inputPort.returnBook(borrowed, library));
    }


    @Test
    void putAwayBookIfReturned() throws LibraryBookInputException {
        LibraryBook shelved = mock(LibraryBook.class);

        LibraryBook returned = mock(LibraryBook.class);
        UUID returnedId = UUID.randomUUID();
        when(returned.getId()).thenReturn(returnedId);
        when(returned.isReturned()).thenReturn(true);

        LibraryShelf shelf = mock(LibraryShelf.class);
        UUID shelfId = UUID.randomUUID();
        when(shelf.getId()).thenReturn(shelfId);

        when(outputPort.findById(returnedId)).thenReturn(Optional.of(returned));
        when(bookFactory.createShelved(returned, shelf)).thenReturn(shelved);

        LibraryBook response = inputPort.putAwayBook(returned, shelf);
        assertEquals(response, shelved);
        verify(outputPort).update(shelved);
    }

    @Test
    void notPutAwayBookIfNotReturned() {
        LibraryBook shelved = mock(LibraryBook.class);

        LibraryBook returned = mock(LibraryBook.class);
        UUID returnedId = UUID.randomUUID();
        when(returned.getId()).thenReturn(returnedId);
        when(returned.isReturned()).thenReturn(false);

        LibraryShelf shelf = mock(LibraryShelf.class);
        UUID shelfId = UUID.randomUUID();
        when(shelf.getId()).thenReturn(shelfId);

        when(outputPort.findById(returnedId)).thenReturn(Optional.of(returned));
        when(bookFactory.createShelved(returned, shelf)).thenReturn(shelved);

        assertThrows(LibraryBookInputException.class, () -> inputPort.putAwayBook(returned, shelf));
    }

}