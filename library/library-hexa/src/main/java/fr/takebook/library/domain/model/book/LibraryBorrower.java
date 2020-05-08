package fr.takebook.library.domain.model.book;

import java.util.UUID;

public class LibraryBorrower {

    private UUID id;

    private String fullName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
