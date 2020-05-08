package fr.takebook.library.infrastructure.in.transport.wrapper;

import fr.takebook.library.domain.model.book.*;
import fr.takebook.library.infrastructure.in.transport.object.LibraryBookDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryBookStateDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryDataBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LibraryBookDTOWrapper {

    @Autowired
    private LibraryDTOWrapper libraryDTOWrapper;

    @Autowired
    private LibraryShelfDTOWrapper libraryShelfDTOWrapper;

    @Autowired
    private LibraryBorrowerDTOWrapper libraryBorrowerDTOWrapper;

    public LibraryBookDTO fromModel(LibraryBook model) {
        LibraryBookDTO dto = new LibraryBookDTO();
        dto.setId(model.getId().toString());
        dto.setData(data(model.getData()));
        dto.setState(type(model.getState()));
        switch (model.getState()) {
            case BORROWED:
                dto.setBorrower(libraryBorrowerDTOWrapper.fromModel(((LibraryBorrowedBook) model).getBorrower()));
                break;
            case RETURNED:
                dto.setLibrary(libraryDTOWrapper.fromModel(((LibraryReturnedBook) model).getLibrary()));
                break;
            case STORED:
                dto.setShelf(libraryShelfDTOWrapper.fromModel(((LibraryStoredBook) model).getShelf()));
                break;
        }
        return dto;
    }

    private LibraryDataBookDTO data(LibraryBookData data) {
        LibraryDataBookDTO dto = new LibraryDataBookDTO();
        dto.setId(data.getId().toString());
        dto.setTitle(data.getTitle());
        dto.setAuthorName(data.getAuthorFullName());
        return dto;
    }

    private LibraryBookStateDTO type(LibraryBookState model) {
        switch (model) {
            case RETURNED:
                return LibraryBookStateDTO.RETURNED;
            case BORROWED:
                return LibraryBookStateDTO.BORROWED;
            case STORED:
                return LibraryBookStateDTO.STORED;
            default:
                return null;
        }
    }


    public LibraryBook toModel(LibraryBookDTO dto) {
        LibraryBook model = new LibraryBook() {
            @Override
            public LibraryBookState getState() {
                return null;
            }
        };
        model.setId(UUID.fromString(dto.getId()));
        return model;
    }
}
