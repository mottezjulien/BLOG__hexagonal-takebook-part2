package fr.takebook.library.infrastructure.out.wrapper;

import fr.takebook.library.domain.model.User;
import fr.takebook.library.domain.model.book.LibraryBorrower;
import org.springframework.stereotype.Component;

@Component
public class LibraryBorrowerWrapper {

    public LibraryBorrower fromUserModel(User user) {
        LibraryBorrower borrower = new LibraryBorrower();
        borrower.setId(user.getId());
        borrower.setFullName(user.getFirstName() + " " + user.getLastName());
        return borrower;
    }
}
