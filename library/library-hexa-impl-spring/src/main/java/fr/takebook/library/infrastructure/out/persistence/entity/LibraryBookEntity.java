package fr.takebook.library.infrastructure.out.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class LibraryBookEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column
    private UUID dataId;

    @Column
    @Enumerated(EnumType.STRING)
    private LibraryBookStateEntity state;

    @ManyToOne
    @JoinColumn
    private LibraryShelfEntity shelf;

    @Column
    private UUID borrowerId;

    @ManyToOne
    @JoinColumn
    private LibraryEntity library;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDataId() {
        return dataId;
    }

    public void setDataId(UUID dataId) {
        this.dataId = dataId;
    }

    public LibraryBookStateEntity getState() {
        return state;
    }

    public void setState(LibraryBookStateEntity state) {
        this.state = state;
    }

    public LibraryShelfEntity getShelf() {
        return shelf;
    }

    public void setShelf(LibraryShelfEntity shelf) {
        this.shelf = shelf;
    }

    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }
}
