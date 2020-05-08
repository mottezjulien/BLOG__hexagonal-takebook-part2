package fr.takebook.library.infrastructure.out.wrapper;

import fr.takebook.library.domain.model.book.LibraryBookState;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookStateEntity;
import org.springframework.stereotype.Component;

@Component
public class LibraryBookStateWrapper {

    public LibraryBookStateEntity toEntity(LibraryBookState model) {
        switch (model){
            case RETURNED:
                return LibraryBookStateEntity.RETURNED;
            case BORROWED:
                return LibraryBookStateEntity.BORROWED;
            case STORED:
                return LibraryBookStateEntity.STORED;
            default:
                return null;
        }
    }

}
