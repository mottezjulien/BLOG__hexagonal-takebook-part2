package fr.takebook.library.infrastructure.in.transport.wrapper;

import fr.takebook.library.domain.model.Library;
import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.infrastructure.in.transport.object.LibraryDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryShelfDTO;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryShelfEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LibraryShelfDTOWrapper {

    @Autowired
    private LibraryDTOWrapper libraryDTOWrapper;

    public LibraryShelfDTO fromEntity(LibraryShelfEntity entity) {
        LibraryShelfDTO dto = new LibraryShelfDTO();
        dto.setId(entity.getId().toString());
        dto.setLabel(entity.getLabel());
        return dto;
    }

    public LibraryShelfDTO fromModel(LibraryShelf model) {
        LibraryShelfDTO dto = new LibraryShelfDTO();
        dto.setId(model.getId().toString());
        dto.setLabel(model.getLabel());
        return dto;
    }

    public LibraryShelf toModel(LibraryShelfDTO dto) {
        LibraryShelf model = new LibraryShelf();
        model.setId(UUID.fromString(dto.getId()));
        model.setLabel(dto.getLabel());
        return model;
    }


}
