package fr.takebook.library.infrastructure.in.transport.object;

public class LibraryShelfDTO {

    private String id;
    private String label;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
