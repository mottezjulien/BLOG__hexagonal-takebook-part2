package fr.takebook.library.domain.ports.out;

import fr.takebook.library.domain.model.book.LibraryBook;
import fr.takebook.library.domain.model.book.LibraryBorrower;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface LibraryBookOutputPort {

    Optional<LibraryBook> findById(UUID id);

    Stream<LibraryBook> findAll();

    void update(LibraryBook book);

    Stream<LibraryBook> findByBorrower(LibraryBorrower borrower);
}
