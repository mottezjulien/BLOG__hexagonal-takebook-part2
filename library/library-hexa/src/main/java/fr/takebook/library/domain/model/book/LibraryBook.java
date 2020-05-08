package fr.takebook.library.domain.model.book;

import java.util.UUID;

public abstract class LibraryBook {

    private UUID id;

    private LibraryBookData data;

    public abstract LibraryBookState getState();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LibraryBookData getData() {
        return data;
    }

    public void setData(LibraryBookData data) {
        this.data = data;
    }

    public boolean isReturned() {
        return getState() == LibraryBookState.RETURNED;
    }

    public boolean isBorrowed() {
        return getState() == LibraryBookState.BORROWED;
    }

    public boolean isStored() {
        return getState() == LibraryBookState.STORED;
    }

}

