package fr.takebook.library.infrastructure.in.controller;

import fr.takebook.library.domain.model.LibraryShelf;
import fr.takebook.library.infrastructure.in.transport.object.LibraryDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryShelfDTO;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryDTOWrapper;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryShelfDTOWrapper;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import fr.takebook.library.infrastructure.out.persistence.repository.LibraryShelfSpringRepository;
import fr.takebook.library.infrastructure.out.persistence.repository.LibrarySpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/takebook/rest/library/shelf")
public class LibraryShelfRestController {

    @Autowired
    private LibraryShelfSpringRepository repository;

    @Autowired
    private LibraryShelfDTOWrapper libraryShelfDTOWrapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/{libraryId}/")
    @ResponseBody
    public Stream<LibraryShelfDTO> findByLibraryId(@PathVariable(value = "libraryId") String libraryId) {
        return StreamSupport
                .stream(repository.findByLibrary(toLibraryEntity(libraryId)).spliterator(), false)
                .map(entity -> libraryShelfDTOWrapper.fromEntity(entity));
    }

    private LibraryEntity toLibraryEntity(String libraryId) {
        LibraryEntity entity = new LibraryEntity();
        entity.setId(UUID.fromString(libraryId));
        return entity;
    }

}