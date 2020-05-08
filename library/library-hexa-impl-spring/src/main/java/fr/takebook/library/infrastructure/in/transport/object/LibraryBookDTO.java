package fr.takebook.library.infrastructure.in.transport.object;

public class LibraryBookDTO {

    private String id;
    private LibraryBookStateDTO state;
    private LibraryShelfDTO shelf;
    private LibraryBorrowerDTO borrower;
    private LibraryDTO library;
    private LibraryDataBookDTO data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LibraryBookStateDTO getState() {
        return state;
    }

    public void setState(LibraryBookStateDTO state) {
        this.state = state;
    }

    public LibraryShelfDTO getShelf() {
        return shelf;
    }

    public void setShelf(LibraryShelfDTO shelf) {
        this.shelf = shelf;
    }

    public LibraryBorrowerDTO getBorrower() {
        return borrower;
    }

    public void setBorrower(LibraryBorrowerDTO borrower) {
        this.borrower = borrower;
    }

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }

    public void setData(LibraryDataBookDTO data) {
        this.data = data;
    }

    public LibraryDataBookDTO getData() {
        return data;
    }
}
