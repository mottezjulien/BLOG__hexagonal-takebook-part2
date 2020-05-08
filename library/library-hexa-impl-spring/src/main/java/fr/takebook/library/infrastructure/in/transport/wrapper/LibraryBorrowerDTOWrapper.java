package fr.takebook.library.infrastructure.in.transport.wrapper;

import fr.takebook.library.domain.model.book.LibraryBorrower;
import fr.takebook.library.infrastructure.in.transport.object.LibraryBorrowerDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryShelfDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LibraryBorrowerDTOWrapper {

    public LibraryBorrowerDTO fromModel(LibraryBorrower model) {
        LibraryBorrowerDTO dto = new LibraryBorrowerDTO();
        dto.setId(model.getId().toString());
        dto.setFullName(model.getFullName());
        return dto;
    }

    public LibraryBorrower toModel(LibraryBorrowerDTO dto) {
        LibraryBorrower model = new LibraryBorrower();
        model.setId(UUID.fromString(dto.getId()));
        model.setFullName(dto.getFullName());
        return model;
    }
}
