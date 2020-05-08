package fr.takebook.data.model;

import java.util.UUID;

public class DataBook {

    private UUID id;
    private String title;
    private DataAuthor author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataAuthor getAuthor() {
        return author;
    }

    public void setAuthor(DataAuthor author) {
        this.author = author;
    }
}
