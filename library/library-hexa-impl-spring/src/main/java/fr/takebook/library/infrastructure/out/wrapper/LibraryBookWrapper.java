package fr.takebook.library.infrastructure.out.wrapper;


import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.domain.model.book.*;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryShelfEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryBookWrapper {

    @Autowired
    private LibraryBookStateWrapper libraryBookStateWrapper;

    public LibraryBookEntity toEntity(LibraryBook model) {
        LibraryBookEntity entity = new LibraryBookEntity();
        entity.setId(model.getId());
        entity.setDataId(model.getData().getId());
        entity.setState(libraryBookStateWrapper.toEntity(model.getState()));
        switch (model.getState()) {
            case BORROWED:
                entity.setBorrowerId(((LibraryBorrowedBook) model).getBorrower().getId());
                break;
            case RETURNED:
                entity.setLibrary(toEntityLibrary(((LibraryReturnedBook) model).getLibrary()));
                break;
            case STORED:
                entity.setShelf(toEntityShelf(((LibraryStoredBook) model).getShelf()));
                break;
        }
        return entity;
    }

    private LibraryEntity toEntityLibrary(Library model) {
        LibraryEntity entity = new LibraryEntity();
        entity.setId(model.getId());
        return entity;
    }

    private LibraryShelfEntity toEntityShelf(LibraryShelf model) {
        LibraryShelfEntity entity = new LibraryShelfEntity();
        entity.setId(model.getId());
        return entity;
    }

    public LibraryBook fromEntity(LibraryBookEntity entity) {
        LibraryBook model = create(entity);
        model.setId(entity.getId());
        LibraryBookData data = new LibraryBookData();
        data.setId(entity.getDataId());
        model.setData(data);
        return model;
    }

    private LibraryBook create(LibraryBookEntity entity) {
        switch (entity.getState()) {
            case RETURNED:
                LibraryReturnedBook libraryReturnedBook = new LibraryReturnedBook();
                Library library = new Library();
                library.setId(entity.getLibrary().getId());
                library.setName(entity.getLibrary().getName());
                libraryReturnedBook.setLibrary(library);
                return libraryReturnedBook;
            case BORROWED:
                LibraryBorrowedBook libraryBorrowedBook = new LibraryBorrowedBook();
                LibraryBorrower borrower = new LibraryBorrower();
                borrower.setId(entity.getBorrowerId());
                libraryBorrowedBook.setBorrower(borrower);
                return libraryBorrowedBook;
            case STORED:
                LibraryStoredBook libraryStoredBook = new LibraryStoredBook();
                LibraryShelf shelf = new LibraryShelf();
                shelf.setId(entity.getShelf().getId());
                shelf.setLabel(entity.getShelf().getLabel());
                libraryStoredBook.setShelf(shelf);
                return libraryStoredBook;
            default:
                throw new IllegalArgumentException();
        }
    }
}
