package fr.takebook.library.infrastructure.in.transport.object;

public class ReturnRequestDTO {

    private LibraryBookDTO book;

    private LibraryDTO library;

    public LibraryBookDTO getBook() {
        return book;
    }

    public void setBook(LibraryBookDTO book) {
        this.book = book;
    }

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }

}
