package fr.takebook.library.domain.model;

import java.util.UUID;

public class LibraryShelf {

    private UUID id;

    private String label;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
