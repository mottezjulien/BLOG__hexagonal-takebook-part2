package fr.takebook.library.domain.model.book;

import fr.takebook.library.domain.model.LibraryShelf;

public class LibraryStoredBook extends LibraryBook {

    private LibraryShelf shelf;

    @Override
    public LibraryBookState getState() {
        return LibraryBookState.STORED;
    }

    public LibraryShelf getShelf() {
        return shelf;
    }

    public void setShelf(LibraryShelf shelf) {
        this.shelf = shelf;
    }

}
