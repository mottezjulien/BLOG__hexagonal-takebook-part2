package fr.takebook.library.infrastructure.in.transport.wrapper;

import fr.takebook.library.domain.model.Library;
import fr.takebook.library.infrastructure.in.transport.object.LibraryDTO;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LibraryDTOWrapper {

    public LibraryDTO fromModel(Library model) {
        LibraryDTO dto = new LibraryDTO();
        dto.setId(model.getId().toString());
        dto.setName(model.getName());
        return dto;
    }

    public LibraryDTO fromEntity(LibraryEntity entity) {
        LibraryDTO dto = new LibraryDTO();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        return dto;
    }

    public Library toModel(LibraryDTO dto) {
        Library model = new Library();
        model.setId(UUID.fromString(dto.getId()));
        model.setName(dto.getName());
        return model;
    }
}
