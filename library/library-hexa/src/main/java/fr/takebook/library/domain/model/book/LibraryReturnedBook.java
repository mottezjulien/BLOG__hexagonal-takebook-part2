package fr.takebook.library.domain.model.book;

import fr.takebook.library.domain.model.Library;

public class LibraryReturnedBook extends LibraryBook {

    private Library library;

    @Override
    public LibraryBookState getState() {
        return LibraryBookState.RETURNED;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }
}
