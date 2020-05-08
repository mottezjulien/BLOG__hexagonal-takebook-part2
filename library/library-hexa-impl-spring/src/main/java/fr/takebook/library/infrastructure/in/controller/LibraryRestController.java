package fr.takebook.library.infrastructure.in.controller;

import fr.takebook.library.infrastructure.in.transport.object.LibraryDTO;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryDTOWrapper;
import fr.takebook.library.infrastructure.out.persistence.repository.LibrarySpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/takebook/rest/library")
public class LibraryRestController {

    @Autowired
    private LibrarySpringRepository repository;

    @Autowired
    private LibraryDTOWrapper libraryDTOWrapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/")
    @ResponseBody
    public Stream<LibraryDTO> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> libraryDTOWrapper.fromEntity(entity));
    }

}