package fr.takebook.library.infrastructure.in.transport.object;

public class PutAwayRequestDTO {

    private LibraryBookDTO book;

    private LibraryShelfDTO shelf;

    public LibraryBookDTO getBook() {
        return book;
    }

    public void setBook(LibraryBookDTO book) {
        this.book = book;
    }

    public LibraryShelfDTO getShelf() {
        return shelf;
    }

    public void setShelf(LibraryShelfDTO shelf) {
        this.shelf = shelf;
    }
}
