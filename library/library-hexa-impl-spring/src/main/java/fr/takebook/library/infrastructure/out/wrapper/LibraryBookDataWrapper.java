package fr.takebook.library.infrastructure.out.wrapper;

import fr.takebook.data.model.DataBook;
import fr.takebook.library.domain.model.book.LibraryBookData;
import org.springframework.stereotype.Component;

@Component
public class LibraryBookDataWrapper {

    public LibraryBookData fromDataModel(DataBook dataBook) {
        LibraryBookData libraryBookData = new LibraryBookData();
        libraryBookData.setId(dataBook.getId());
        libraryBookData.setTitle(dataBook.getTitle());
        libraryBookData.setAuthorFullName(dataBook.getAuthor().getFirstName() + " " + dataBook.getAuthor().getLastName());
        return libraryBookData;
    }

}
